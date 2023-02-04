package com.hackathon.javatemplate.validation.impl;

import com.hackathon.javatemplate.auth.constants.ServiceConstants;
import com.hackathon.javatemplate.auth.constants.ValidatorErrorConstants;
import com.hackathon.javatemplate.auth.entities.User;
import com.hackathon.javatemplate.auth.repository.UserRepository;
import com.hackathon.javatemplate.util.RegexValidator;
import com.hackathon.javatemplate.validation.annotations.UpdateSampleEntityRequestValidator;
import com.hackathon.javatemplate.vo.requestVo.UpdateSampleEntityRequestVo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.UUID;

import static com.hackathon.javatemplate.auth.constants.ServiceConstants.EMAIL_REGEX_PATTERN;
import static com.hackathon.javatemplate.auth.constants.ValidatorErrorConstants.EMPTY_USERNAME;
import static com.hackathon.javatemplate.auth.constants.ValidatorErrorConstants.USERNAME_ALREADY_EXIST;

public class UpdateSampleEntityRequestValidatorImpl implements ConstraintValidator<UpdateSampleEntityRequestValidator, UpdateSampleEntityRequestVo> {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RegexValidator regexValidator;

    @Override
    public boolean isValid(UpdateSampleEntityRequestVo requestVo, ConstraintValidatorContext context) {
        try {

            if (requestVo.getId() == null) {
                invalidDataException(context, ValidatorErrorConstants.EMPTY_ID);
                return false;
            }
            if (requestVo.getUserName() == null || requestVo.getUserName().isBlank()) {
                invalidDataException(context, EMPTY_USERNAME);
                return false;
            }
            UUID id = userRepository.findIdByUserName(requestVo.getUserName());
            if (id != null && !id.equals(requestVo.getId())){
                invalidDataException(context, USERNAME_ALREADY_EXIST);
                return false;
            }
            if (requestVo.getEmail() == null || requestVo.getEmail().isBlank()) {
                invalidDataException(context, ValidatorErrorConstants.EMPTY_EMAIL);
                return false;
            }

            if (regexValidator.notVerifiedRegex(requestVo.getEmail(), EMAIL_REGEX_PATTERN)) {
                invalidDataException(context, ValidatorErrorConstants.INVALID_EMAIL_PATTERN);
                return false;
            }
            if (requestVo.getPhoneNumber() == null || requestVo.getPhoneNumber().isBlank()) {
                invalidDataException(context, ValidatorErrorConstants.EMPTY_PHONE_NUMBER);
                return false;
            }
            if (regexValidator.notVerifiedRegex(requestVo.getPhoneNumber(), ServiceConstants.PHONE_NUMBER_REGEX_PATTERN)) {
                invalidDataException(context, ValidatorErrorConstants.INVALID_PHONE_NUMBER_PATTERN);
                return false;
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void invalidDataException(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }

}
