package org.helmo.HolyD.controlers.exception;

public class UserNotFoundException extends RuntimeException {

    public static final String MESSAGE_ERROR = "Could not find user";
    public UserNotFoundException() {
        super(MESSAGE_ERROR);
    }
}