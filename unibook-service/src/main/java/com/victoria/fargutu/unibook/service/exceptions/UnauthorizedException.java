package com.victoria.fargutu.unibook.service.exceptions;

import com.victoria.fargutu.unibook.service.commons.ExceptionType;

public class UnauthorizedException extends UnibookException {

    public UnauthorizedException() {
        super("Username or password invalid!", ExceptionType.AUTHORIZATION_REQUIRED);
    }
}
