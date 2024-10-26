package me.sora.younote.controller;

import me.sora.younote.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

@SpringBootTest
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void whenCallingGetUser_thenReturnUser() throws Exception {
        // Given
        var userId = UUID.randomUUID();
        // When
        Mockito.when(userService.getUserByUserId(Mockito.any(UUID.class)))
                .thenReturn(null);

        // Then
    }
}
