package org.helmo.HolyD.repository.exception;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(){
        super("User already exist");
    }
}
