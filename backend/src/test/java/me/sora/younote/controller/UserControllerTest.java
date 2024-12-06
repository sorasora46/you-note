package me.sora.younote.controller;

import me.sora.younote.constant.ErrorConstant;
import me.sora.younote.constant.ServiceConstant;
import me.sora.younote.controller.advice.ApplicationException;
import me.sora.younote.dto.CommonResponse;
import me.sora.younote.dto.user.GetUserByIdResponse;
import me.sora.younote.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void whenCallingGetUserByUserId_thenReturnUser() throws Exception {
        // Given
        var userId = UUID.randomUUID();
        var mockResult = GetUserByIdResponse.builder()
                .id(userId)
                .build();

        // When
        Mockito.when(userService.getUserByUserId(Mockito.any(UUID.class)))
                .thenReturn(mockResult);

        // Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/users/" + userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status")
                        .value(ServiceConstant.RESPONSE_STATUS.SUCCESS))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.id")
                        .value(userId.toString()));
    }

    @Test
    void whenCallingGetUserByUserId_andNotFound_thenReturnException() throws Exception {
        // Given
        var userId = UUID.randomUUID();
        var message = ErrorConstant.USER_NOT_FOUND + ":" + userId;
        var exception = new ApplicationException(message, HttpStatus.NOT_FOUND);

        // When
        Mockito.when(userService.getUserByUserId(Mockito.any(UUID.class)))
                .thenThrow(exception);

        // Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/users/" + userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status")
                        .value(ServiceConstant.RESPONSE_STATUS.FAILED))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(message));
    }
}
