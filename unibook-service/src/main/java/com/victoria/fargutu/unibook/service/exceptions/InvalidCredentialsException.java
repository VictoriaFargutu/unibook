package com.victoria.fargutu.unibook.service.exceptions;

import com.victoria.fargutu.unibook.service.commons.ExceptionType;

public class InvalidCredentialsException extends UnibookException {
    public InvalidCredentialsException(String message) {
        super(message, ExceptionType.AUTHORIZATION_REQUIRED);
    }
}
