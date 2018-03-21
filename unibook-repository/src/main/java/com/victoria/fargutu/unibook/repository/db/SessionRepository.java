package com.victoria.fargutu.unibook.repository.db;

import com.victoria.fargutu.unibook.repository.model.auth.AuthSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<AuthSession, Long> {
    AuthSession findBySessionToken(String token);
}
