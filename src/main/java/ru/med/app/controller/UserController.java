package ru.med.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.med.app.dto.TokenResponse;
import ru.med.app.dto.request.UserCreateRequest;
import ru.med.app.dto.request.UserUpdateRequest;
import ru.med.app.dto.response.user.UserResponse;
import ru.med.app.dto.response.user.UserShortResponse;
import ru.med.app.service.impl.UserServiceImpl;

import java.util.UUID;

@RestController
@RequestMapping("med")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/user")
    public TokenResponse register(@RequestBody @Valid UserCreateRequest body) {
        return userService.createUser(body);
    }

    @GetMapping("/user")
    public UserResponse getProfile(Authentication authentication) {
        return userService.getUserResponse(authentication);
    }

    @GetMapping("/user/{userId}")
    public UserShortResponse findById(@PathVariable String userId) {
        UUID id = UUID.fromString(userId);
        return userService.getUserShortResponse(id);
    }

    @PatchMapping("/user")
    public UserResponse updateUserInfo(@RequestBody @Valid UserUpdateRequest body, Authentication authentication) {
        return userService.updateUser(body, authentication);
    }
}
