package org.helmo.HolyD.controlers.exception;

public class UserAlreadyExistException extends RuntimeException {

    public static final String MESSAGE_ERROR = "User already exist";

    public UserAlreadyExistException(){
        super(MESSAGE_ERROR);
    }
}
