package com.magic.academy.cursedpotions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when attempting to add a potion with a name that already exists.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class PotionAlreadyExistsException extends RuntimeException {
    /**
     * Constructs the exception with a descriptive error message.
     */
    public PotionAlreadyExistsException() {
        super("A potion with this name already exists");
    }
}