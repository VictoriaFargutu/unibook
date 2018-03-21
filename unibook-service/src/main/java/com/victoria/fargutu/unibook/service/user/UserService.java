package com.victoria.fargutu.unibook.service.user;

import com.victoria.fargutu.unibook.repository.model.user.User;

public interface UserService {
    User createUser(User user);

    User getUserById(Long id);

    User updateUser(Long id, User user);

    void deleteUserById(Long id);
}
