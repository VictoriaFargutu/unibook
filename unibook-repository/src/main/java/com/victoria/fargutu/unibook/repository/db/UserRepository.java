package com.victoria.fargutu.unibook.repository.db;

import com.victoria.fargutu.unibook.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
