package com.victoria.fargutu.unibook.service.validation;

import com.victoria.fargutu.unibook.repository.model.user.User;
import com.victoria.fargutu.unibook.service.commons.Constant;
import com.victoria.fargutu.unibook.service.commons.Field;
import com.victoria.fargutu.unibook.service.exceptions.InvalidFieldException;
import com.victoria.fargutu.unibook.service.exceptions.UnibookException;
import org.springframework.stereotype.Component;

/**
 * Created by razvan on 23/01/2018.
 */

@Component
public class UserValidator {
    public UnibookException getValidationException(User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return new InvalidFieldException("Password field not completed", Field.PASSWORD);
        } else if (!user.getPassword().matches(Constant.PASSWORD_PATTERN)) {
            return new InvalidFieldException("Password is not valid. Needs to have at least 8 characters, a letter, a digit and no whitespace.", Field.PASSWORD);
        } else return getUpdateValidationException(user);
    }

    public UnibookException getUpdateValidationException(User user) {
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
            return new InvalidFieldException("First name field not completed", Field.FIRST_NAME);
        } else if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            return new InvalidFieldException("Last name field not completed", Field.LAST_NAME);
        } else if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return new InvalidFieldException("Email field not completed", Field.EMAIL);
        } else if (!user.getEmail().trim().matches(Constant.EMAIL_PATTERN)) {
            return new InvalidFieldException("Invalid email", Field.EMAIL);
        } else if (user.getRole() == null) {
            return new InvalidFieldException("User must have a role", Field.ROLE);
        }
        return null;
    }


}
