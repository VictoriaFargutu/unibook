package com.victoria.fargutu.unibook.service.exceptions;

import com.victoria.fargutu.unibook.service.commons.ExceptionType;
import com.victoria.fargutu.unibook.service.commons.Field;

public class UnibookException extends RuntimeException {

    private ExceptionType exceptionType;
    private Field field;

    public UnibookException(String message) {
    }

    public UnibookException(String message, ExceptionType exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public UnibookException(String message, ExceptionType exceptionType, Field field) {
        super(message);
        this.exceptionType = exceptionType;
        this.field = field;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public Field getField() {
        return field;
    }
}
