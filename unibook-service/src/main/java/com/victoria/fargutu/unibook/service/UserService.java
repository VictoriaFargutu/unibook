package com.victoria.fargutu.unibook.service;

import com.victoria.fargutu.unibook.repository.model.User;

public interface UserService {
    User createUser(User user);

    User getUserById(Long id);

    User updateUser(Long id, User user);

    void deleteUserById(Long id);
}
