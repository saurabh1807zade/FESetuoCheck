package com.hackathon.javatemplate.validation.annotations;

import com.hackathon.javatemplate.auth.constants.ValidatorErrorConstants;
import com.hackathon.javatemplate.validation.impl.SampleEntityRequestValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.hackathon.javatemplate.auth.constants.ValidatorErrorConstants.DEFAULT_INVALID_BODY;
import static java.lang.annotation.ElementType.*;

@Target( {TYPE, FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SampleEntityRequestValidatorImpl.class)
public @interface SampleEntityRequestValidator {

    public String message() default DEFAULT_INVALID_BODY;

    //Represents group of constraints
    public Class<?>[] groups() default {};

    //Represents additional information about annotation
    public Class<? extends Payload>[] payload() default {};
}
