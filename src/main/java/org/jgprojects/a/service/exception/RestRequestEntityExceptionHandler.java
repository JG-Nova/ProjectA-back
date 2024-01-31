package org.jgprojects.a.service.exception;

public class RestRequestEntityExceptionHandler extends RuntimeException{
    public RestRequestEntityExceptionHandler(String message){
        super(message);
    }
    public RestRequestEntityExceptionHandler(String message, Throwable cause){
        super(message, cause);
    }
}
