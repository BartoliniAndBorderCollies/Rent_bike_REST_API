package com.klodnicki.bike.rest.API.Bike.exception;

public class RestException {

    private final String message;
    private final String reason;

    public RestException(String message, String reason) {
        this.message = message;
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public String getReason() {
        return reason;
    }
}
