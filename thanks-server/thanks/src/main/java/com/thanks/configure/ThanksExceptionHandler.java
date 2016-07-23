package com.thanks.configure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by micky on 2016. 7. 18..
 */
@ControllerAdvice
public class ThanksExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnauthorizedUserException.class)
    public ResponseEntity<String> handleUnauthorizedUser (HttpServletRequest req, Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }


}
