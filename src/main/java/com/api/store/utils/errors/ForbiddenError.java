package com.api.store.utils.errors;

public class ForbiddenError extends RuntimeException {
    public ForbiddenError() {
        super("You are not allowed to access this");
    }
}
