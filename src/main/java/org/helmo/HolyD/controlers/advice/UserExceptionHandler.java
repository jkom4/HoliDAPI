package org.helmo.HolyD.controlers.advice;

import org.helmo.HolyD.controlers.exception.DateTimeRangeIsNotARangeException;
import org.helmo.HolyD.controlers.exception.UserAlreadyExistException;
import org.helmo.HolyD.controlers.exception.UserAlreadyInsideException;
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
    Error userAlreadyExistHandler(UserAlreadyExistException ex) {
        return ex.getERROR();
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Error userNotFoundHandler(UserNotFoundException ex) {
        return ex.getERROR();
    }

    @ResponseBody
    @ExceptionHandler(UserAlreadyInsideException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Error userAlreadyInsideHandler(UserAlreadyInsideException ex) {
        return ex.getERROR();
    }

    @ResponseBody
    @ExceptionHandler(DateTimeRangeIsNotARangeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Error dateTimeRangeIsNotARangeExceptionHandler(DateTimeRangeIsNotARangeException ex) {
        return ex.getERROR();
    }
}
