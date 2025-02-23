package me.sora.younote.controller;

import me.sora.younote.constant.ServiceConstant;
import me.sora.younote.controller.advice.ApplicationException;
import me.sora.younote.dto.user.GetUserByIdResponse;
import me.sora.younote.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static me.sora.younote.constant.ErrorConstant.ErrorCode.ERROR_CODE_USER_NOT_FOUND;
import static me.sora.younote.constant.ErrorConstant.ErrorMessage.ERROR_MESSAGE_USER_NOT_FOUND;
import static me.sora.younote.constant.ServiceConstant.ResponseStatus.SERVICE_ERROR;

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
                .status(ServiceConstant.ResponseStatus.SUCCESS)
                .data(GetUserByIdResponse.GetUserByIdData.builder()
                        .id(userId)
                        .build())
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
                        .value(ServiceConstant.ResponseStatus.SUCCESS))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id")
                        .value(userId.toString()));
    }

    @Test
    void whenCallingGetUserByUserId_andNotFound_thenReturnException() throws Exception {
        // Given
        var userId = UUID.randomUUID();
        var exception = new ApplicationException(SERVICE_ERROR, ERROR_CODE_USER_NOT_FOUND, ERROR_MESSAGE_USER_NOT_FOUND);

        // When
        Mockito.when(userService.getUserByUserId(Mockito.any(UUID.class)))
                .thenThrow(exception);

        // Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/users/" + userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status")
                        .value(SERVICE_ERROR))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error.code")
                        .value(ERROR_CODE_USER_NOT_FOUND))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error.message")
                        .value(ERROR_MESSAGE_USER_NOT_FOUND));
    }
}
