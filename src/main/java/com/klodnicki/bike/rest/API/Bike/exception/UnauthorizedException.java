package com.klodnicki.bike.rest.API.Bike.exception;

public class UnauthorizedException extends Exception{

    private static final String MESSAGE = "You are not authorized to see that!";

    public UnauthorizedException() {
        super(String.format(MESSAGE));
    }
}
