package com.hackathon.javatemplate.auth.service.impl;

import com.hackathon.javatemplate.auth.config.TokenProvider;
import com.hackathon.javatemplate.auth.dto.UserDTO;
import com.hackathon.javatemplate.auth.entities.RefreshToken;
import com.hackathon.javatemplate.auth.entities.User;
import com.hackathon.javatemplate.auth.enums.Role;
import com.hackathon.javatemplate.auth.mapper.UserMapper;
import com.hackathon.javatemplate.auth.repository.RefreshTokenRepository;
import com.hackathon.javatemplate.auth.repository.UserRepository;
import com.hackathon.javatemplate.auth.service.AuthTokenService;
import com.hackathon.javatemplate.auth.service.PasswordService;
import com.hackathon.javatemplate.vo.requestvo.RefreshTokenRequest;
import com.hackathon.javatemplate.vo.requestvo.UserLoginRequest;
import com.hackathon.javatemplate.vo.requestvo.UserRegisterRequest;
import com.hackathon.javatemplate.vo.responseVo.AuthToken;
import com.hackathon.javatemplate.customResponse.CustomResponse;
import com.hackathon.javatemplate.exception.CustomBadRequestException;
import com.hackathon.javatemplate.exception.CustomUnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

import static com.hackathon.javatemplate.auth.constants.ConfigConstants.BEARER;
import static com.hackathon.javatemplate.auth.constants.ConfigConstants.THOUSAND;
import static com.hackathon.javatemplate.auth.constants.ResponseErrors.*;
import static com.hackathon.javatemplate.auth.constants.ResponseSuccess.*;

@Service
public class AuthTokenServiceImpl implements AuthTokenService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    PasswordService passwordService;

    @Autowired
    UserMapper userMapper;

    public static final String TOKEN_PREFIX = BEARER;

    @Value("${jwt.token.validity}")
    public Long TOKEN_VALIDITY;

    @Value("${jwt.token.refresh.validity}")
    public Long REFRESH_TOKEN_VALIDITY;

    @Override
    public void registerSuperAdmin(UserDTO userDTO) {
        User user = userMapper.mapUserFromDTO(userDTO, Role.ADMIN.name);
        userRepository.save(user);
    }

    @Override
    public CustomResponse<?> login(String userType, UserLoginRequest userLoginRequest) {

        if (invalidUserType(userType)) {
            throw new CustomBadRequestException(INVALID_USER_TYPE);
        }

        String user = userLoginRequest.getUser();

        //Get User
        Optional<User> optionalUser = userRepository.findFirstByUserTypeAndIsDisableAndUserNameOrEmailOrPhoneOrderByUpdatedAtDesc(userType, false, user, user, user);
        if (optionalUser.isEmpty()) {
            throw new CustomUnauthorizedException(INVALID_CREDENTIAL);
        }
        User dbUser = optionalUser.get();

        if (!dbUser.getIsActive()) {
            throw new CustomUnauthorizedException(INACTIVE_USER);
        }

        //Verify password
        if (!passwordService.verifyPassword(userLoginRequest.getPassword(), dbUser.getPassword())) {
            throw new CustomUnauthorizedException(INVALID_CREDENTIAL);
        }
        //Password Verified
        AuthToken authToken = createToken(userLoginRequest, dbUser);
        return new CustomResponse<>(HttpStatus.OK, authToken, LOGIN_SUCCESS);
    }

    @Override
    public CustomResponse<?> refreshToken(String userType, RefreshTokenRequest refreshTokenRequest) {
        if (invalidUserType(userType)) {
            throw new CustomBadRequestException(INVALID_USER_TYPE);
        }
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByRefreshTokenAndUser_UserType(refreshTokenRequest.getRefreshToken(), userType);
        if (optionalRefreshToken.isEmpty()) {
            throw new CustomUnauthorizedException(INVALID_REFRESH_TOKEN);
        }
        RefreshToken refreshToken = optionalRefreshToken.get();
        if (refreshToken.getExpiry().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new CustomUnauthorizedException(INVALID_REFRESH_TOKEN);
        }
        User user = refreshToken.getUser();
        String newAccessToken = jwtTokenUtil.generateTokenFromOld(refreshToken.getAccessToken(), user.getUserName(), user.getId());
        RefreshToken newRefreshToken = createRefreshToken(user, newAccessToken);
        AuthToken authToken = new AuthToken(user.getId(), TOKEN_PREFIX, newAccessToken, TOKEN_VALIDITY, newRefreshToken.getRefreshToken(), REFRESH_TOKEN_VALIDITY);
        return new CustomResponse<>(HttpStatus.OK, authToken, REFRESH_TOKEN_SUCCESS);
    }

    @Override
    public CustomResponse<?> registerUser(String userType, UserRegisterRequest userRegisterRequest) {
        if (invalidUserType(userType)) {
            throw new CustomBadRequestException(INVALID_USER_TYPE);
        }
        User user = userMapper.mapUserFromRequestVo(userRegisterRequest, userType);
        userRepository.save(user);
        return new CustomResponse<>(HttpStatus.OK, USER_REGISTER_SUCCESS);
    }


    @Override
    public CustomResponse<String> resetPasswordViaOTP(Object resetPasswordCred) {
        return null;
    }

    private AuthToken createToken(UserLoginRequest userLoginRequest, User dbUser) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dbUser.getUserName(),
                        userLoginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication, dbUser);
        RefreshToken refreshToken = createRefreshToken(dbUser, token);
        return new AuthToken(dbUser.getId(), TOKEN_PREFIX, token, TOKEN_VALIDITY, refreshToken.getRefreshToken(), REFRESH_TOKEN_VALIDITY);
    }

    private RefreshToken createRefreshToken(User user, String accessToken) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setAccessToken(accessToken);
        refreshToken.setExpiry(Instant.now().plusMillis(REFRESH_TOKEN_VALIDITY * THOUSAND));
        refreshToken.setRefreshToken(jwtTokenUtil.generateRefreshToken());
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    private boolean invalidUserType(String userType) {
        try {
            for (final Role value : Role.values()) {
                if (value.name.equals(userType)) return false;
            }
            return true;
        } catch (Exception e) {
            return true;
        }
    }

}
