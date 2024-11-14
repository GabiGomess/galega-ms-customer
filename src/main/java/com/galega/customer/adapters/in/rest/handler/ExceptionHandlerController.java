package com.galega.customer.adapters.in.rest.handler;

import com.galega.customer.adapters.in.rest.dto.ErrorDTO;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException(IllegalArgumentException exception) {
        ErrorDTO errorResponse = new ErrorDTO("Parameters or request body is invalid");
        return ResponseEntity.badRequest().body(errorResponse);
    }
}