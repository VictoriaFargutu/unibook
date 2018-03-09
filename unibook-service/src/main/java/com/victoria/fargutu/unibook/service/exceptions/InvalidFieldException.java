package com.victoria.fargutu.unibook.service.exceptions;

import com.victoria.fargutu.unibook.service.commons.ExceptionType;
import com.victoria.fargutu.unibook.service.commons.Field;

public class InvalidFieldException extends UnibookException {

    public InvalidFieldException(String message, Field field) {
        super(message, ExceptionType.INVALID_FIELD, field);
    }

}
