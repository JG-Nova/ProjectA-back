package org.jgprojects.a.service.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(value = {RestRequestEntityExceptionHandler.class})
    public ResponseEntity<Object> handleRestRequestException(RestRequestEntityExceptionHandler e){
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        RestExceptionHandler restExceptionHandler = new RestExceptionHandler(e.getMessage(), e, httpStatus, ZonedDateTime.now());
        return new ResponseEntity<>(restExceptionHandler, httpStatus);
    }
}
