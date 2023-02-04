package com.hackathon.javatemplate.exception;

import com.hackathon.javatemplate.customResponse.CustomResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionHelper {

    @ExceptionHandler(value = CustomBadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public CustomResponse<?> exception(CustomBadRequestException e) {
        return new CustomResponse<>(BAD_REQUEST, e, e.message);
    }

    @ExceptionHandler(value = CustomUnauthorizedException.class)
    @ResponseStatus(UNAUTHORIZED)
    public CustomResponse<?> exception(CustomUnauthorizedException exception) {
        return new CustomResponse<>(UNAUTHORIZED, exception, exception.message);
    }

    @ExceptionHandler(value = CustomForbiddenException.class)
    @ResponseStatus(FORBIDDEN)
    public CustomResponse<?> exception(CustomForbiddenException exception) {
        return new CustomResponse<>(FORBIDDEN, exception, FORBIDDEN.getReasonPhrase());
    }


}
