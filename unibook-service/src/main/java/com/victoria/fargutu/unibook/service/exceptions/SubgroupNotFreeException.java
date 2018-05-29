package com.victoria.fargutu.unibook.service.exceptions;

import com.victoria.fargutu.unibook.service.commons.ExceptionType;

public class SubgroupNotFreeException extends UnibookException {

    public SubgroupNotFreeException() {
        super("This subgroup is not free for the selected dates!", ExceptionType.GROUP_NOT_FREE);
    }
}
