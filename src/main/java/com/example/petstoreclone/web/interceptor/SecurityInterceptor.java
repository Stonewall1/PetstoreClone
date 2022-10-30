package com.example.petstoreclone.web.interceptor;

import com.example.petstoreclone.exceptions.NoTokenFoundException;
import com.example.petstoreclone.entity.User;
import com.example.petstoreclone.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class SecurityInterceptor implements HandlerInterceptor {
    private final UserRepository userRepository;

    public SecurityInterceptor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("X-Token");
        Optional<User> byToken = userRepository.findByToken(token);
        if (byToken.isPresent()) {
            return true;
        } else throw new NoTokenFoundException();
    }
}
