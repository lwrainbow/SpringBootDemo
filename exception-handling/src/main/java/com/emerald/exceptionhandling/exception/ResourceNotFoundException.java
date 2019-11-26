package com.emerald.exceptionhandling.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * The ResourceNotFoundException is customized exception
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
