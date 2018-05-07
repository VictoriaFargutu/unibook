package com.victoria.fargutu.unibook.api.interceptor;

import com.victoria.fargutu.unibook.repository.commons.UserRole;
import com.victoria.fargutu.unibook.repository.model.auth.AuthManager;
import com.victoria.fargutu.unibook.repository.model.auth.AuthSession;
import com.victoria.fargutu.unibook.repository.model.user.User;
import com.victoria.fargutu.unibook.service.auth.AuthService;
import com.victoria.fargutu.unibook.service.exceptions.UnauthorizedException;
import com.victoria.fargutu.unibook.service.security.HasRole;
import com.victoria.fargutu.unibook.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private AuthService authService;
    private AuthManager authManager;
    private UserService userService;

    @Autowired
    public AuthInterceptor(AuthService authService, AuthManager authManager, UserService userService) {
        this.authService = authService;
        this.authManager = authManager;
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            String token = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("X-Auth-Token")) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }
            if (token == null) {
                token = request.getHeader("X-Auth-Token");
            }
            if (method.getMethod().getAnnotation(HasRole.class) == null) {
                return true;
            } else if (token != null) {
                AuthSession authSession = authService.validateToken(token);
                User user = userService.getUserById(authSession.getUserId());
                authManager.setLoggedInUser(user);
                authManager.setAuthSession(authSession);
                UserRole userRole = method.getMethod().getAnnotation(HasRole.class).value();
                if (!user.isHigher(userRole)) {
                    throw new UnauthorizedException();
                }
            } else if (method.getMethod().getAnnotation(HasRole.class) != null) {
                throw new UnauthorizedException();
            }
        }
        return true;
    }
}
