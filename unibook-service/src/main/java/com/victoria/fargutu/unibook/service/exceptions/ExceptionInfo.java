package com.victoria.fargutu.unibook.service.exceptions;

import com.victoria.fargutu.unibook.service.commons.ExceptionType;
import com.victoria.fargutu.unibook.service.commons.Field;

public class ExceptionInfo {
    private ExceptionType type;
    private String message;
    private Field field;

    public ExceptionInfo(ExceptionType exceptionType, String message, Field field) {
        this.type = exceptionType;
        this.message = message;
        this.field = field;
    }

    public ExceptionInfo(ExceptionType exceptionType, String message) {
        this.type = exceptionType;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public ExceptionType getType() {
        return type;
    }

    public void setType(ExceptionType type) {
        this.type = type;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
