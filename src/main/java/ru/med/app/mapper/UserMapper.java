package ru.med.app.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.med.app.dto.request.UserCreateRequest;
import ru.med.app.dto.response.user.UserResponse;
import ru.med.app.dto.response.user.UserShortResponse;
import ru.med.app.entity.User;

import java.time.LocalDateTime;

@Component
public class UserMapper {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static User mapRegisterRequestToUser(UserCreateRequest body) {
        User user = new User();
        user.setLastName(body.lastName());
        user.setFirstName(body.firstName());
        user.setMiddleName(body.middleName());
        user.setSex(body.sex());
        user.setBirthDate(body.birthDate());
        user.setAddress(body.address());
        user.setEmail(body.email());
        user.setPhoneNumber(body.phoneNumber());
        user.setPassword(passwordEncoder.encode(body.password()));
        user.setCreatedTime(LocalDateTime.now());

        return user;
    }

    public static UserResponse mapUserToResponse(User user) {
        return new UserResponse(
                user.getId().toString(),
                user.getLastName(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getSex(),
                user.getBirthDate(),
                user.getAddress(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getCreatedTime(),
                user.getUpdatedTime()
        );
    }

    public static UserShortResponse mapUserToShortResponse(User user) {
        return new UserShortResponse(
                user.getLastName(),
                user.getFirstName(),
                user.getEmail(),
                user.getPassword()
        );
    }
}
