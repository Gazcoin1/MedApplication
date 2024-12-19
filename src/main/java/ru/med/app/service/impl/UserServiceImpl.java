package ru.med.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.med.app.dto.TokenResponse;
import ru.med.app.dto.request.UserCreateRequest;
import ru.med.app.dto.request.UserUpdateRequest;
import ru.med.app.dto.response.user.UserResponse;
import ru.med.app.dto.response.user.UserShortResponse;
import ru.med.app.entity.User;
import ru.med.app.mapper.UserMapper;
import ru.med.app.repository.UserRepository;
import ru.med.app.utils.JwtTokenUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserDetailsService {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;
    private final JwtTokenUtils jwtTokenUtils;

    @Transactional
    public TokenResponse createUser(UserCreateRequest body) {
        User user = UserMapper.mapRegisterRequestToUser(body);
        log.info("Creating user: {}", user);
        isUserCreated(user);

        userRepository.save(user);

        //TODO ДОБАВЬ ПОТОМ СЕССИЮ

        String token = jwtTokenUtils.generateToken(user);

        log.info("Generated token: {}", token);

        return TokenResponse.builder().token(token).build();
    }

    public UserResponse getUserResponse(Authentication authentication) {
//        UUID id = jwtTokenUtils.getUserIdFromAuthentication(authentication);
//        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserMapper.mapUserToResponse(getUserByAuthentication(authentication));
    }

    public User getUserByAuthentication(Authentication authentication) {
        UUID id = jwtTokenUtils.getUserIdFromAuthentication(authentication);
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserShortResponse getUserShortResponse(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserMapper.mapUserToShortResponse(user);
    }

    public UserResponse updateUser(UserUpdateRequest body, Authentication authentication) {
        UUID id = jwtTokenUtils.getUserIdFromAuthentication(authentication);
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (body.lastName() != null) {
            user.setLastName(body.lastName());
        }
        if (body.firstName() != null) {
            user.setFirstName(body.firstName());
        }
        if (body.middleName() != null) {
            user.setMiddleName(body.middleName());
        }
        if (body.birthDate() != null) {
            user.setBirthDate(body.birthDate());
        }
        if (body.password() != null) {
            user.setPassword(passwordEncoder.encode(body.password()));
        }

        user.setUpdatedTime(LocalDateTime.now());
        userRepository.save(user);
        return UserMapper.mapUserToResponse(user);
    }

    private void isUserCreated(User user) {
        if (user.getFirstName() == null) {
            throw new UsernameNotFoundException("User is not created");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
