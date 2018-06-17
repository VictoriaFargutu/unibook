package com.victoria.fargutu.unibook.service.user;

import com.victoria.fargutu.unibook.repository.commons.UserRole;
import com.victoria.fargutu.unibook.repository.db.UserRepository;
import com.victoria.fargutu.unibook.repository.model.user.User;
import com.victoria.fargutu.unibook.service.commons.ExceptionType;
import com.victoria.fargutu.unibook.service.exceptions.NotFoundException;
import com.victoria.fargutu.unibook.service.exceptions.UnibookException;
import com.victoria.fargutu.unibook.service.security.EncryptionManager;
import com.victoria.fargutu.unibook.service.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserValidator userValidator;
    private EncryptionManager encryptionManager;

    @Autowired
    UserServiceImpl(UserRepository userRepository, UserValidator userValidator, EncryptionManager encryptionManager) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.encryptionManager = encryptionManager;
    }

    @Override
    public User createUser(User user) {
        user.setRole(UserRole.USER);
        RuntimeException exception = userValidator.getValidationException(user);
        if (exception != null) {
            throw exception;
        }

        arrangeFields(user);

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UnibookException("Email already in use!", ExceptionType.EMAIL_IN_USE);
        }

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new NotFoundException("User Not Found!");
        }
        return user;
    }

    private void arrangeFields(User user) {
        user.setFirstName(user.getFirstName().trim());
        user.setLastName(user.getLastName().trim());
        user.setEmail(user.getEmail().trim().toLowerCase());
        user.setPassword(encryptionManager.encrypt(user.getPassword()));
    }


    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new NotFoundException("User Not Found!");
        }
        userRepository.delete(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findOne(id);
        if (existingUser == null) {
            throw new NotFoundException("User Not Found!");
        }

        RuntimeException exception = userValidator.getUpdateValidationException(user);
        if (exception != null) {
            throw exception;
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UnibookException("Email already in use!", ExceptionType.EMAIL_IN_USE);
        }
        arrangeFields(user);

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
