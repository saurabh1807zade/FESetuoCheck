package com.hackathon.javatemplate.auth.service.impl;

import com.hackathon.javatemplate.auth.config.TokenProvider;
import com.hackathon.javatemplate.auth.service.Validator;
import com.hackathon.javatemplate.exception.CustomForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.hackathon.javatemplate.auth.constants.ConfigConstants.ROLE_;

@Component
public class ValidatorImpl implements Validator {


    @Autowired
    TokenProvider tokenProvider;


    @Override
    public void validateSelfToken(String authToken, UUID requestId, String permitRole) {
        List<String> rolesAssigned = tokenProvider.getRolesFromToken(authToken);
        if (rolesAssigned.contains(ROLE_ + permitRole)) {
            return;
        }

        String userIdFromToken = tokenProvider.getUserIdFromToken(authToken);
        if (requestId.toString().equals(userIdFromToken)) {
            return;
        }
        throw new CustomForbiddenException();
    }


}
