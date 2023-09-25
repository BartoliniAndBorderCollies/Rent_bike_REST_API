package com.klodnicki.bike.rest.API.Bike.exception;

public class NotFoundInDatabaseException extends Exception{

    private static final String MESSAGE = "Zadane zapytanie nie znalazło wyników.";

    public NotFoundInDatabaseException() {
        super(String.format(MESSAGE));
    }
}
