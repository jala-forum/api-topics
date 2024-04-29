package com.api.store.utils.errors;

public class GenericError extends RuntimeException {
    public GenericError(String message) {
        super(message);
    }
}
