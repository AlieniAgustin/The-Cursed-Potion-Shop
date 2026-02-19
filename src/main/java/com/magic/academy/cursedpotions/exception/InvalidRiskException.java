package com.magic.academy.cursedpotions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a potion's risk level is outside the valid range (1-100).
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRiskException extends RuntimeException {
    /**
     * Constructs the exception with a descriptive error message.
     */
    public InvalidRiskException() {
        super("Risk level must be between 1 and 100");
    }
}