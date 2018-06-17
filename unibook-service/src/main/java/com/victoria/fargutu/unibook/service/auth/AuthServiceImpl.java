package com.victoria.fargutu.unibook.service.auth;

import com.victoria.fargutu.unibook.repository.db.SessionRepository;
import com.victoria.fargutu.unibook.repository.db.UserRepository;
import com.victoria.fargutu.unibook.repository.model.AuthManager;
import com.victoria.fargutu.unibook.repository.model.auth.AuthSession;
import com.victoria.fargutu.unibook.repository.model.auth.Credentials;
import com.victoria.fargutu.unibook.repository.model.user.User;
import com.victoria.fargutu.unibook.repository.model.user.UserResponse;
import com.victoria.fargutu.unibook.service.commons.Constant;
import com.victoria.fargutu.unibook.service.commons.Field;
import com.victoria.fargutu.unibook.service.exceptions.InvalidCredentialsException;
import com.victoria.fargutu.unibook.service.exceptions.InvalidFieldException;
import com.victoria.fargutu.unibook.service.exceptions.UnauthorizedException;
import com.victoria.fargutu.unibook.service.security.EncryptionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private EncryptionManager encryptionManager;
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private AuthManager authManager;


    @Autowired
    public AuthServiceImpl(SessionRepository sessionRepository, EncryptionManager encryptionManager, UserRepository userRepository, AuthManager authManager) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.encryptionManager = encryptionManager;
        this.authManager = authManager;
    }

    @Override
    public AuthSession createAuthSession(@NotNull User user) {
        String initialToken = user.getEmail() + ":" + System.currentTimeMillis() + ":" + user.getEmail();
        String token = encryptionManager.encrypt(initialToken);
        if (token == null) {
            throw new RuntimeException("Session could not be created!");
        }
        AuthSession authSession = new AuthSession();
        authSession.setSessionToken(token);
        authSession.setExpirationTime(System.currentTimeMillis() + 2 * 60 * 60 * 1000 * 24);
        authSession.setUserId(user.getId());
        return sessionRepository.save(authSession);
    }

    @Override
    public AuthSession validateToken(String token) {
        AuthSession authSession = sessionRepository.findBySessionToken(token);
        if (authSession != null && authSession.getExpirationTime() > System.currentTimeMillis()) {
            return authSession;
        } else {
            throw new UnauthorizedException();
        }
    }

    @Override
    public AuthSession verifyCredentials(String authToken) {
        String[] tmp = authToken.split(" ");
        String decryptedData;
        if (tmp[0].equals("Basic")) {
            decryptedData = encryptionManager.decrypt(tmp[1]);
        } else {
            throw new InvalidCredentialsException("Invalid credentials!");
        }
        String[] userData = decryptedData.split(":");
        if (userData.length != 2
                || userData[0] == null || userData[1] == null
                || userData[0].isEmpty() || userData[1].isEmpty()) {
            throw new InvalidCredentialsException("Invalid credentials!");
        }
        Credentials credentials = new Credentials();
        credentials.setEmail(userData[0]);
        credentials.setPassword(encryptionManager.encrypt(userData[1]));

        User user = userRepository.findByEmail(credentials.getEmail());

        if (user == null || !user.getPassword().equals(credentials.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials!");
        }
        AuthSession authSession = createAuthSession(user);

        return authSession;
    }

    @Override
    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void invalidateSession() {
        List<AuthSession> sessions = sessionRepository.findAll();
        for (AuthSession session : sessions) {
            if (session.getExpirationTime() <= System.currentTimeMillis()) {
                sessionRepository.delete(session);
            }
        }
    }

    @Override
    public void logout() {
        sessionRepository.delete(authManager.getAuthSession());
    }

    @Override
    public UserResponse resetPassword(String newPassword) {
        String decryptedData = encryptionManager.decrypt(newPassword);
        if (!decryptedData.matches(Constant.PASSWORD_PATTERN)) {
            throw new InvalidFieldException("Password is not valid. Needs to have at least 8 characters, a letter, a digit and no whitespace.", Field.PASSWORD);
        }
        User user = userRepository.findOne(authManager.getLoggedInUser().getId());
        user.setPassword(encryptionManager.encrypt(decryptedData));
        UserResponse userResponse = new UserResponse(userRepository.save(user));
        return userResponse;
    }
}
