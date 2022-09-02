package com.usermanagement.backend.exception;

import lombok.Data;

@Data
public class GeneralRuntimeException extends RuntimeException {

    public GeneralRuntimeException(String message) {
        super(message);
    }
}
