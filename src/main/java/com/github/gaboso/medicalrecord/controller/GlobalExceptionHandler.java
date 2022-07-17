package com.github.gaboso.medicalrecord.controller;

import com.github.gaboso.medicalrecord.exception.EmptyFileException;
import com.github.gaboso.medicalrecord.exception.NotFoundException;
import com.github.gaboso.medicalrecord.exception.UnsupportedFileException;
import com.github.gaboso.medicalrecord.exception.template.ErrorTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        log.error("Unexpected exception: ", ex);
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorTemplate> handler(NotFoundException e) {
        return createErrorTemplateResponseEntity(e, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmptyFileException.class)
    public ResponseEntity<ErrorTemplate> handler(EmptyFileException e) {
        return createErrorTemplateResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UnsupportedFileException.class)
    public ResponseEntity<ErrorTemplate> handler(UnsupportedFileException e) {
        return createErrorTemplateResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected ResponseEntity<ErrorTemplate> createErrorTemplateResponseEntity(Exception exception, HttpStatus httpStatus) {
        log.error("Handling " + exception + " with status " + httpStatus, exception);

        String message = exception.getMessage();

        ErrorTemplate build = ErrorTemplate.builder()
                                           .errorCode(httpStatus.name())
                                           .errorMessage(message)
                                           .build();

        return ResponseEntity.status(httpStatus).body(build);
    }

}
