package org.example.spring_boot_learning.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

//ControllerAdvice is a specialization of the @Component annotation which allows to handle exceptions across the whole application in one global handling component. It can be viewed as an interceptor of exceptions thrown by methods annotated with @RequestMapping and similar.
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError>handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request){
        Map<String,String> fields = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ApiError body = new ApiError(
                Instant.now(),
                400,
                "Validation Error",
                "Validation failed for one or more fields",
                request.getRequestURI(),
                fields
        );
        return ResponseEntity.badRequest().body(body);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError>handleNotFound(NotFoundException ex, HttpServletRequest request) {
        ApiError body = new ApiError(
                Instant.now(),
                404,
                "Not Found",
                ex.getMessage(),
                request.getRequestURI(),
                null
        );
        return ResponseEntity.status(404).body(body);
    }
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError>handleConflict(ConflictException ex, HttpServletRequest request) {
        ApiError body = new ApiError(
                Instant.now(),
                409,
                "Conflict",
                ex.getMessage(),
                request.getRequestURI(),
                null
        );
        return ResponseEntity.status(409).body(body);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError>handleConflict(DataIntegrityViolationException ex, HttpServletRequest request) {
        ApiError body = new ApiError(
                Instant.now(),
                409,
                "Data Integrity Violation",
                ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage(),
                request.getRequestURI(),
                null
        );
        return ResponseEntity.status(409).body(body);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError>handleException(Exception ex, HttpServletRequest request) {
        ApiError body = new ApiError(
                Instant.now(),
                500,
                "Internal Server Error",
                ex.getMessage(),
                request.getRequestURI(),
                null
        );
        return ResponseEntity.status(500).body(body);
    }
}

