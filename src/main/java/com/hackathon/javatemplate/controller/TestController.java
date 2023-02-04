package com.hackathon.javatemplate.controller;

import com.hackathon.javatemplate.auth.config.TokenProvider;
import com.hackathon.javatemplate.auth.enums.Permissions;
import com.hackathon.javatemplate.auth.service.Validator;
import com.hackathon.javatemplate.customResponse.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.UUID;

import static com.hackathon.javatemplate.auth.constants.ApiConstants.*;
import static com.hackathon.javatemplate.auth.constants.ConfigConstants.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(ROOT_URL_TEST)
public class TestController extends AbstractController {

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    Validator validator;


    public String TOKEN_PREFIX = BEARER;

    public static final String HEADER_STRING = AUTHORIZATION;


    @GetMapping(USERS_BY_ID)
    @ResponseStatus(OK)
    @RolesAllowed({Permissions.PermissionConstants.USER_READ, Permissions.PermissionConstants.USER_READ_SELF})
    public CustomResponse<?> getUserById(@PathVariable UUID id, @RequestHeader(HEADER_STRING) String bearerToken) {

        String authToken = bearerToken.replace(TOKEN_PREFIX, EMPTY_STRING);
        String userIdFromToken = tokenProvider.getUserIdFromToken(authToken);
        validator.validateSelfToken(authToken, id, Permissions.PermissionConstants.USER_READ);
        String response = "User id = "+id+". Requester Id = "+userIdFromToken; //Call Service here.
        return new CustomResponse<>(OK, response);

    }

    @GetMapping(ADMINS_BY_ID)
    @ResponseStatus(OK)
    @RolesAllowed({Permissions.PermissionConstants.ADMIN_READ, Permissions.PermissionConstants.ADMIN_READ_SELF})
    public CustomResponse<?> getAdminById(@PathVariable UUID id, @RequestHeader(HEADER_STRING) String bearerToken) {

        String authToken = bearerToken.replace(TOKEN_PREFIX, EMPTY_STRING);
        String userIdFromToken = tokenProvider.getUserIdFromToken(authToken);
        validator.validateSelfToken(authToken, id, Permissions.PermissionConstants.ADMIN_READ);
        String response = "Admin id = "+id+". Requester Id = "+userIdFromToken; //Call Service here.
        return new CustomResponse<>(OK, response);

    }



}
