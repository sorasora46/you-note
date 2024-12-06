package me.sora.younote.controller;

import lombok.RequiredArgsConstructor;
import me.sora.younote.dto.CommonResponse;
import me.sora.younote.dto.user.GetUserByIdResponse;
import me.sora.younote.service.UserService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CommonResponse<GetUserByIdResponse>> getUserByUserId(@PathVariable UUID userId) {
        GetUserByIdResponse response = userService.getUserByUserId(userId);
        return ResponseEntity.ok().body(CommonResponse.success(response));
    }
}
