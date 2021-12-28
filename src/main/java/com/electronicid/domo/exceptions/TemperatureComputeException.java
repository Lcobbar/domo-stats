package com.electronicid.domo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TemperatureComputeException extends Exception {

    private final String message;

    public TemperatureComputeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
