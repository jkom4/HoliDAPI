package org.helmo.HolyD.controlers.exception;

import org.helmo.HolyD.models.reponses.Error;

public class DateTimeRangeIsNotARangeException extends RuntimeException {

    public static final String STATUCODE_ERROR = "400";
    public static final String ERROR_ERROR = "Bad request";
    public static final String MESSAGE_ERROR = "The begin datTime of the range is bigger than the end dateTime";

    private final Error ERROR;

    public DateTimeRangeIsNotARangeException(){
        super(MESSAGE_ERROR);
        this.ERROR = new Error(STATUCODE_ERROR, ERROR_ERROR, MESSAGE_ERROR);
    }
    public Error getERROR() {
        return ERROR;
    }
}