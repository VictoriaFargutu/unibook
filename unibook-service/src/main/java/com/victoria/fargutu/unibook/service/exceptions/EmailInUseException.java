package com.victoria.fargutu.unibook.service.exceptions;

import com.victoria.fargutu.unibook.service.commons.ExceptionType;
import com.victoria.fargutu.unibook.service.commons.Field;

public class EmailInUseException extends UnibookException {

    public EmailInUseException() {
        super("Email already in use!", ExceptionType.EMAIL_IN_USE, Field.EMAIL);
    }
}
