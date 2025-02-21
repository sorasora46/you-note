package me.sora.younote.service;

import lombok.RequiredArgsConstructor;
import me.sora.younote.controller.advice.ApplicationException;
import me.sora.younote.dto.user.GetUserByIdResponse;
import me.sora.younote.entity.User;
import me.sora.younote.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static me.sora.younote.constant.ErrorConstant.ErrorCode.ERROR_CODE_USER_NOT_FOUND;
import static me.sora.younote.constant.ErrorConstant.ErrorMessage.ERROR_MESSAGE_USER_NOT_FOUND;
import static me.sora.younote.constant.ServiceConstant.ResponseStatus.SERVICE_ERROR;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public GetUserByIdResponse getUserByUserId(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(SERVICE_ERROR, ERROR_CODE_USER_NOT_FOUND, ERROR_MESSAGE_USER_NOT_FOUND));
        return GetUserByIdResponse.builder()
                .data(GetUserByIdResponse.GetUserByIdData.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .build())
                .build();
    }

}
