package org.helmo.HolyD.controlers.advice;

import org.helmo.HolyD.controlers.exception.*;
import org.helmo.HolyD.models.reponses.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControlerExceptionHandler {
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
    @ExceptionHandler(DateTimeIntervalIsNotAIntervalException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Error dateTimeIntervalIsNotAIntervalExceptionHandler(DateTimeIntervalIsNotAIntervalException ex) {
        return ex.getERROR();
    }

    @ResponseBody
    @ExceptionHandler(VacanceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Error vacanceNotFoundHandler(VacanceNotFoundException ex) {
        return ex.getERROR();
    }

    @ResponseBody
    @ExceptionHandler(ActiviteNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Error activiteNotFoundHandler(ActiviteNotFoundException ex) {
        return ex.getERROR();
    }
}
