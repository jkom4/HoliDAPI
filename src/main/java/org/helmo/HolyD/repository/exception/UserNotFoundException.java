package org.helmo.HolyD.repository.exception;

public class UserNotFoundException extends RuntimeException {

    UserNotFoundException() {
        super("Could not find user ");
    }
}