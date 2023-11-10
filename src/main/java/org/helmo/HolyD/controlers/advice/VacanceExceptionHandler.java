package org.helmo.HolyD.controlers.advice;

import org.helmo.HolyD.controlers.exception.VacanceNotFoundException;
import org.helmo.HolyD.models.reponses.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VacanceExceptionHandler {

    @ResponseBody
    @ExceptionHandler(VacanceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Error vacanceNotFoundHandler(VacanceNotFoundException ex) {
        return ex.getERROR();
    }
}
