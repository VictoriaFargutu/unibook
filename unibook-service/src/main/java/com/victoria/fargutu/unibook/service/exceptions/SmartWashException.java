package com.victoria.fargutu.unibook.service.exceptions;

import com.victoria.fargutu.unibook.service.commons.ExceptionType;
import com.victoria.fargutu.unibook.service.commons.Field;

public class SmartWashException extends RuntimeException {

    private ExceptionType exceptionType;
    private Field field;

    public SmartWashException(String message) {
    }

    public SmartWashException(String message, ExceptionType exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public SmartWashException(String message, ExceptionType exceptionType, Field field) {
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
