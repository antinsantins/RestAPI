package org.antins.restapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class StringException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
}
