package com.victoria.fargutu.unibook.api.interceptor;

import com.victoria.fargutu.unibook.repository.commons.UserRole;
import com.victoria.fargutu.unibook.repository.model.AuthManager;
import com.victoria.fargutu.unibook.repository.model.User;
import com.victoria.fargutu.unibook.service.AuthService;
import com.victoria.fargutu.unibook.service.exceptions.UnauthorizedException;
import com.victoria.fargutu.unibook.service.security.HasRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private AuthService authService;
    private AuthManager authManager;

    @Autowired
    public AuthInterceptor(AuthService authService, AuthManager authManager) {
        this.authService = authService;
        this.authManager = authManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            String token = request.getHeader("X-Auth-Token");
            if (method.getMethod().getAnnotation(HasRole.class) == null) {
                return true;
            } else if (token != null) {
                User user = authService.validateToken(token);
                authManager.setLoggedInUser(user);
                UserRole userRole = method.getMethod().getAnnotation(HasRole.class).value();
                if (user.getRole() != userRole) {
                    throw new UnauthorizedException();
                }
            } else if (method.getMethod().getAnnotation(HasRole.class) != null) {
                throw new UnauthorizedException();
            }
        }
        return true;
    }
}
