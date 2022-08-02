package com.example.trucker.exceptions;

public class TRUCKerListError extends Exception{
    public TRUCKerListError(String message, Throwable cause){
        super(message, cause);
    }
    public TRUCKerListError(String message){
        super(message);
    }
}
