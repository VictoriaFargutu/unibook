package com.victoria.fargutu.unibook.service.exceptions;

import com.victoria.fargutu.unibook.service.commons.ExceptionType;

public class ClassroomNotFreeException extends UnibookException {

    public ClassroomNotFreeException() {
        super("This classroom is not free for the selected dates!", ExceptionType.CLASSROOM_NOT_FREE);
    }
}