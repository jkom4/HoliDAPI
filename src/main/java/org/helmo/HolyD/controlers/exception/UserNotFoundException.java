package org.helmo.HolyD.controlers.exception;

import org.helmo.HolyD.models.reponses.Error;

public class UserNotFoundException extends RuntimeException {

    public static final String STATUCODE_ERROR = "404";
    public static final String ERROR_ERROR = "Not found";
    public static final String MESSAGE_ERROR = "Could not find user";

    private final Error ERROR;

    public UserNotFoundException() {
        super(MESSAGE_ERROR);
        this.ERROR = new Error(STATUCODE_ERROR, ERROR_ERROR, MESSAGE_ERROR);
    }
    public Error getERROR() {
        return ERROR;
    }
}