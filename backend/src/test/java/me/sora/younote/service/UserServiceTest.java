package me.sora.younote.service;

import me.sora.younote.controller.advice.ApplicationException;
import me.sora.younote.entity.User;
import me.sora.younote.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static me.sora.younote.constant.ErrorConstant.ErrorCode.ERROR_CODE_USER_NOT_FOUND;
import static me.sora.younote.constant.ErrorConstant.ErrorMessage.ERROR_MESSAGE_USER_NOT_FOUND;
import static me.sora.younote.constant.ServiceConstant.ResponseStatus.SERVICE_ERROR;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void whenCallingGetUserByUserId_thenReturnUser() {
        // Given
        var userId = UUID.randomUUID();
        var user = new User();
        user.setId(userId);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");

        // When
        Mockito.when(userRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(user));

        // Then
        var response = userService.getUserByUserId(userId);
        var data = response.getData();
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(data);
        Assertions.assertEquals(userId, data.getId());
        Assertions.assertEquals(user.getFirstName(), data.getFirstName());
        Assertions.assertEquals(user.getLastName(), data.getLastName());
        Assertions.assertEquals(user.getEmail(), data.getEmail());
    }

    @Test
    void whenCallingGetUserByUserId_thenReturnException() {
        // Given
        var userId = UUID.randomUUID();
        var exception = new ApplicationException(SERVICE_ERROR, ERROR_CODE_USER_NOT_FOUND, ERROR_MESSAGE_USER_NOT_FOUND);

        // When
        Mockito.when(userRepository.findById(Mockito.any(UUID.class))).thenThrow(exception);

        // Then
        var response = Assertions.assertThrows(ApplicationException.class, () -> userService.getUserByUserId(userId));
        Assertions.assertNotNull(response);
        Assertions.assertEquals(SERVICE_ERROR, response.getStatus());
        Assertions.assertEquals(ERROR_CODE_USER_NOT_FOUND, response.getCode());
        Assertions.assertEquals(ERROR_MESSAGE_USER_NOT_FOUND, response.getMessage());
    }
}
