package com.api.store.utils.errors;

public class InvalidParamError extends RuntimeException {

    public InvalidParamError(String field) {
        super("Invalid param " + field);
    }
}
