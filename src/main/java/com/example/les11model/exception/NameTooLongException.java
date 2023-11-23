package com.example.les11model.exception;

public class NameTooLongException extends RuntimeException {
    public NameTooLongException(String message){
        super(message);
    }
}
