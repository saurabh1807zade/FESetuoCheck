package com.hackathon.javatemplate.auth.service;

import com.hackathon.javatemplate.auth.dto.UserDTO;
import com.hackathon.javatemplate.customResponse.CustomResponse;
import com.hackathon.javatemplate.vo.requestvo.RefreshTokenRequest;
import com.hackathon.javatemplate.vo.requestvo.UserLoginRequest;
import com.hackathon.javatemplate.vo.requestvo.UserRegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthTokenService {

    void registerSuperAdmin(UserDTO userDTO);

    CustomResponse<?> login(String userType, UserLoginRequest userLoginRequest);

    CustomResponse<?> refreshToken(String userType, RefreshTokenRequest loginUser);

    CustomResponse<?> registerUser(String userType, UserRegisterRequest userRegisterRequest);

    CustomResponse<String> resetPasswordViaOTP(Object resetPasswordCred);

}
