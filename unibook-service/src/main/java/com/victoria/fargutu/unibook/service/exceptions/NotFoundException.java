package com.victoria.fargutu.unibook.service.exceptions;

import com.victoria.fargutu.unibook.service.commons.ExceptionType;

public class NotFoundException extends UnibookException {
    public NotFoundException(String message) {
        super(message, ExceptionType.NOT_FOUND);
    }
}
