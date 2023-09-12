package org.antins.restapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StringExceptionHandler {
    @ExceptionHandler(value = {StringEmptyException.class})
    public ResponseEntity<Object> handleStringEmptyException(StringEmptyException stringEmptyException) {
        StringException stringException = new StringException(
                stringEmptyException.getMessage(),
                stringEmptyException.getCause(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(stringException, stringException.getHttpStatus());
    }
}
