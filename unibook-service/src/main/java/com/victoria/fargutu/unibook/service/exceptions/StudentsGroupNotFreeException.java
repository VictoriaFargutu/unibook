package com.victoria.fargutu.unibook.service.exceptions;

import com.victoria.fargutu.unibook.service.commons.ExceptionType;

public class StudentsGroupNotFreeException extends UnibookException {

    public StudentsGroupNotFreeException() {
        super("This students' group is not free for the selected dates!", ExceptionType.GROUP_NOT_FREE);
    }
}
