package com.victoria.fargutu.unibook.api.exception;

import com.victoria.fargutu.unibook.service.commons.ExceptionType;
import com.victoria.fargutu.unibook.service.exceptions.ExceptionInfo;
import com.victoria.fargutu.unibook.service.exceptions.UnibookException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UnibookExceptionHandler {

    @ExceptionHandler(UnibookException.class)
    public ResponseEntity handleException(UnibookException exception) {

        if (exception.getExceptionType().equals(ExceptionType.INVALID_FIELD)) {
            ExceptionInfo exceptionInfo = new ExceptionInfo(exception.getExceptionType(), exception.getMessage(), exception.getField());

            return ResponseEntity.status(400).body(exceptionInfo);
        }
        if (exception.getExceptionType().equals(ExceptionType.EMAIL_IN_USE)) {
            ExceptionInfo exceptionInfo = new ExceptionInfo(exception.getExceptionType(), exception.getMessage());

            return ResponseEntity.status(409).body(exceptionInfo);
        }
        if (exception.getExceptionType().equals(ExceptionType.NOT_FOUND)) {
            ExceptionInfo exceptionInfo = new ExceptionInfo(exception.getExceptionType(), exception.getMessage());

            return ResponseEntity.status(404).body(exceptionInfo);
        }
        if (exception.getExceptionType().equals(ExceptionType.AUTHORIZATION_REQUIRED)) {
            ExceptionInfo exceptionInfo = new ExceptionInfo(exception.getExceptionType(), exception.getMessage());

            return ResponseEntity.status(401).body(exceptionInfo);
        }
        return null;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGenericException(Exception exception) {
        ExceptionInfo exceptionInfo = new ExceptionInfo(ExceptionType.INTERNAL_ERROR, exception.getMessage());
        exception.printStackTrace();
        return ResponseEntity.status(500).body(exceptionInfo);
    }

}
