package com.example.management;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFormAuthentication() throws Exception {
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "alice@mail.co")
                        .param("password", "pass"))
                .andExpect(status().isOk()) // or redirect status if using default behavior
                .andExpect(authenticated().withUsername("alice@mail.co"));
    }

    @Test
    public void testJsonAuthentication() throws Exception {
        String jsonBody = """ 
                {
                    "username": "alice@mail.co",
                    "password": "pass"
                }
                """;

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(authenticated().withUsername("alice@mail.co"));
    }

    @Test
    public void LoginApiV1_wrongPassword() throws Exception {
        String jsonBody = """ 
                {
                    "username": "John.Doe@mail.co",
                    "password": "password"
                }
                """;

        mockMvc.perform(post("/api/v1/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isUnauthorized());
    }
    @Test
    public void LoginApiV1_emptyPassword() throws Exception {
        String jsonBody = """ 
                {
                    "username": "John.Doe@mail.co",
                    "password": ""
                }
                """;

        mockMvc.perform(post("/api/v1/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void registerShortPassword() throws Exception {
        String jsonBody = """ 
                {
                    "name": "new user",
                    "username": "John.Doe@mail.co",
                    "password": "pass"
                }
                """;

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isBadRequest());
    }
}
