package org.spring.as.quickstarts.kitchensink;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        Map<String, String> responseObj = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
                responseObj.put(violation.getPropertyPath().toString(), violation.getMessage())
        );
        return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
        Map<String, String> responseObj = new HashMap<>();
        responseObj.put("error", ex.getMessage());
        return new ResponseEntity<>(responseObj, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        Map<String, String> responseObj = new HashMap<>();
        responseObj.put("error", ex.getMessage());
        return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
    }
}
