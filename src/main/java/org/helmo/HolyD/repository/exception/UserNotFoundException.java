package org.helmo.HolyD.repository.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Could not find user ");
    }
}