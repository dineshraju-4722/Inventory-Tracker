package com.inventory_tracker.backend.exceptions;

public class APIException extends RuntimeException{
    public APIException(String message){
        super(message);
    }
}
