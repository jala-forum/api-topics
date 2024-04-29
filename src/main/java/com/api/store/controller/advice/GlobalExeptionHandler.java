package com.api.store.controller.advice;

import com.api.store.utils.errors.ForbiddenError;
import com.api.store.utils.errors.GenericError;
import com.api.store.utils.errors.InvalidParamError;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class GlobalExeptionHandler {
    @ExceptionHandler(InvalidParamError.class)
    public ResponseEntity<String> handleInvalidParamException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(GenericError.class)
    public ResponseEntity<String> handleGenericException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ForbiddenError.class)
    public ResponseEntity<String> handleForbiddenException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }
}
