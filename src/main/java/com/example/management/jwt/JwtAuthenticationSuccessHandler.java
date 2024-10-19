package com.example.management.jwt;

import com.example.management.user.AppUser;
import com.example.management.auth.AuthResponse;
import com.example.management.user.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    JwtService jwtService;
    ObjectMapper objectMapper;
    public JwtAuthenticationSuccessHandler(JwtService jwtService) {
        this.jwtService = jwtService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        AppUser user = (AppUser) authentication.getPrincipal();
        UserDTO userDTO = new UserDTO(user.getName(), user.getUsername());
        String token = jwtService.generateToken(user);

        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new AuthResponse(userDTO, token)));
        response.getWriter().flush();

        //        response.sendRedirect("/home");
    }
}
