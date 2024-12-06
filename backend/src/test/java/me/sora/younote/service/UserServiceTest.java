package me.sora.younote.service;

import me.sora.younote.constant.ErrorConstant;
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
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.UUID;

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
        Assertions.assertNotNull(response);
        Assertions.assertEquals(userId, response.getId());
        Assertions.assertEquals(user.getFirstName(), response.getFirstName());
        Assertions.assertEquals(user.getLastName(), response.getLastName());
        Assertions.assertEquals(user.getEmail(), response.getEmail());
    }

    @Test
    void whenCallingGetUserByUserId_thenReturnException() {
        // Given
        var userId = UUID.randomUUID();
        var message = ErrorConstant.USER_NOT_FOUND + ":" + userId;
        var exception = new ApplicationException(message, HttpStatus.NOT_FOUND);

        // When
        Mockito.when(userRepository.findById(Mockito.any(UUID.class))).thenThrow(exception);

        // Then
        var response = Assertions.assertThrows(ApplicationException.class, () -> userService.getUserByUserId(userId));
        Assertions.assertNotNull(response);
        Assertions.assertEquals(message, response.getMessage());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getHttpStatus());
    }
}
