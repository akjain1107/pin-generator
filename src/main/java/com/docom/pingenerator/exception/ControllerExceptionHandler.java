package com.docom.pingenerator.exception;

import com.docom.pingenerator.model.MSISDNResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(InputValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MSISDNResponse invalidInputRequest(InputValidationException ex, WebRequest request) {
        return new MSISDNResponse(ex.getMessage());
    }
}
