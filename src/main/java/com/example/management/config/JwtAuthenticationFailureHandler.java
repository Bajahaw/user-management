package com.example.management.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

public class JwtAuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {
    Logger log = org.slf4j.LoggerFactory.getLogger(JwtAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("Authentication failed", exception);

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write(exception.getMessage());
        response.getWriter().flush();


    }
}
