package me.sora.younote.service;

import lombok.RequiredArgsConstructor;
import me.sora.younote.constant.ErrorConstant;
import me.sora.younote.controller.advice.ApplicationException;
import me.sora.younote.dto.user.GetUserByIdResponse;
import me.sora.younote.entity.User;
import me.sora.younote.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public GetUserByIdResponse getUserByUserId(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(ErrorConstant.USER_NOT_FOUND + ":" + userId,
                        HttpStatus.NOT_FOUND));
        return GetUserByIdResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

}
