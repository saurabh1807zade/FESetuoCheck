package com.hackathon.javatemplate.auth.service;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface Validator {

    void validateSelfToken(String authToken, UUID requestId, String permitRole);

}
