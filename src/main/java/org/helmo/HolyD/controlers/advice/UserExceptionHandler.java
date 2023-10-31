package org.helmo.HolyD.controlers.advice;

import org.helmo.HolyD.controlers.exception.UserAlreadyExistException;
import org.helmo.HolyD.controlers.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.helmo.HolyD.models.reponses.Error;

@RestControllerAdvice
public class UserExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Error userNotFoundHandler(UserAlreadyExistException ex) {
        return ex.getERROR();
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Error userNotFoundHandler(UserNotFoundException ex) {
        return ex.getERROR();
    }
}
