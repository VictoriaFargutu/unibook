package com.victoria.fargutu.unibook.service;

import com.victoria.fargutu.unibook.repository.model.User;

public interface UserService {
    User getUserById(Long id);

    User updateUser(Long id);
}
