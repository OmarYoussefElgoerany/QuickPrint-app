package com.screen.quickprint.common.exception.handler;


import com.screen.quickprint.common.exception.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.nio.file.AccessDeniedException;
import java.util.*;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        Set<String> errors = new HashSet<>();

        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var errorMessage = error.getDefaultMessage();
                    errors.add(errorMessage);
                });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .validationErrors(errors)
                                .businessErrorDescription("Validation Errors")
                                .build()
                );

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ExceptionResponse.builder()
                                .error(exception.getMessage())
                                .businessErrorDescription("No founded Entity ")
                                .build()
                );
    }


    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ExceptionResponse> IllegalStateException(IllegalStateException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .error(exception.getMessage())
                                .businessErrorDescription(exception.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException exp) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .error(exp.getMessage())
                                .build()
                );
    }

//    @ExceptionHandler(MessagingException.class)
//    public ResponseEntity<ExceptionResponse> handleMessagingException(MessagingException exp) {
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(
//                        ExceptionResponse.builder()
//                                .error(exp.getMessage())
//                                .build()
//                );
//    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(ResourceNotFoundException exception) {
        logger.error("Resource not found: {}", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ExceptionResponse.builder()
                                .error(exception.getMessage())
                                .businessErrorDescription("Requested resource not found")
                                .build()
                );
    }

    @ExceptionHandler(DuplicateNameException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateName(DuplicateNameException ex) {
        logger.error("Handling DuplicateNameException Exception: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .error(ex.getMessage())
                                .businessErrorDescription("Duplicate name exception")
                                .build()
                );
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> accessDeniedException(AccessDeniedException exception) {
        logger.error("Handling Access Denied Exception: {}", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(
                        ExceptionResponse.builder()
                                .error(exception.getMessage())
                                .businessErrorDescription("Not Authorized for this action")
                                .build()
                );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> runTimeException(RuntimeException exception) {
        logger.error("Handling RunTime Exception: {}", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .error(exception.getMessage())
                                .businessErrorDescription("Generic RunTime Exception")
                                .build()
                );
    }

    @ExceptionHandler(DataAccessFailureException.class)
    public ResponseEntity<ExceptionResponse> handleDataAccessFailure(DataAccessFailureException ex) {
        logger.error("Data access failure: {}", ex.getMessage(), ex); // Include stack trace in logs

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE) // 503 for data access issues
                .body(
                        ExceptionResponse.builder()
                                .error(ex.getMessage())
                                .businessErrorDescription("Database operation failed")
                                .build()
                );
    }

    @ExceptionHandler(BusinessValidationException.class)
    public ResponseEntity<ExceptionResponse> handleBusinessValidation(BusinessValidationException ex) {
        logger.warn("Business validation failed: {} - {}", ex.getMessage(), ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(
                        ExceptionResponse.builder()
                                .error(ex.getMessage())
                                .businessErrorDescription("Business validation failed")
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        logger.error("Unexpected exception occurred", exception);

        ExceptionResponse response = ExceptionResponse.builder()
                .error(exception.getMessage() != null ? exception.getMessage() : "Unexpected internal error")
                .businessErrorDescription("An unexpected error occurred. Please contact support.")
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolation(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .error(ex.getMessage())
                                .businessErrorDescription("Constraint Violation Exception")
                                .build()
                );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .error(ex.getMessage())
                                .businessErrorDescription("Request body is missing or malformed")
                                .build()
                );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Throwable rootCause = ex.getRootCause();
        String message = "Database error occurred.";

        if (rootCause != null && rootCause.getMessage() != null) {
            String rootMsg = rootCause.getMessage();

            if (rootMsg.contains("Duplicate entry")) {
                String constraint = extractConstraintFromMessage(rootMsg);
                String userField = convertConstraintToUserField(constraint);
                message = userField + " must be unique";
            } else if (rootMsg.toLowerCase().contains("foreign key constraint fails")) {
                message = "Related data missing or referenced incorrectly";
            } else {
                message = rootMsg;
            }
        }

        logger.error("Data integrity violation: ", ex);

        ExceptionResponse response = ExceptionResponse.builder()
                .businessErrorDescription("Database Exception")
                .error(message)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


//    @ExceptionHandler(UnauthorizedException.class)
//    public ResponseEntity<ExceptionResponse> InvalidUnauthorizedException(UnauthorizedException exception) {
//        logger.error("Handling UnauthorizedException: {}", exception.getMessage());
//        return ResponseEntity
//                .status(HttpStatus.UNAUTHORIZED)
//                .body(
//                        ExceptionResponse.builder()
//                                .error(exception.getMessage())
//                                .businessErrorDescription("User Not Authenticated")
//                                .build()
//                );
//    }


    private String extractConstraintFromMessage(String msg) {
        try {
            int start = msg.indexOf("for key '") + "for key '".length();
            int end = msg.indexOf("'", start);
            return msg.substring(start, end);
        } catch (Exception e) {
            return "value";
        }
    }

    private String convertConstraintToUserField(String constraint) {
        if (constraint == null || constraint.isEmpty()) {
            return "Value";
        }

        // Strip table prefix if constraint includes a dot and contains "uk"
        if (constraint.contains("uk") && constraint.contains(".")) {
            constraint = constraint.substring(constraint.indexOf('.') + 1);
        }

        // Remove leading "uk_" if present
        if (constraint.startsWith("uk_")) {
            constraint = constraint.substring(3);
        }

        // Convert parts to title-case words
        String[] parts = constraint.split("[._]");
        return Arrays.stream(parts)
                .map(this::capitalize)
                .collect(Collectors.joining(" "));
    }

    //**********
    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<Map<String, Object>> handleFileStorageException(FileStorageException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", ex.getMessage());
        response.put("type", "FILE_STORAGE_ERROR");

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleFileNotFoundException(FileNotFoundException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", ex.getMessage());
        response.put("type", "FILE_NOT_FOUND");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Map<String, Object>> handleMaxUploadSizeExceeded(MaxUploadSizeExceededException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", "File size exceeds maximum allowed size");
        response.put("type", "FILE_SIZE_EXCEEDED");

        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(response);
    }

    private String capitalize(String word) {
        if (word == null || word.isEmpty()) return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

}
