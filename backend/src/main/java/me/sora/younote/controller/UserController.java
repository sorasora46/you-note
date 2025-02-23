package me.sora.younote.controller;

import lombok.RequiredArgsConstructor;
import me.sora.younote.dto.user.GetUserByIdResponse;
import me.sora.younote.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public GetUserByIdResponse getUserByUserId(@PathVariable UUID userId) {
        return userService.getUserByUserId(userId);
    }
}
