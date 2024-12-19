package ru.med.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.med.app.dto.request.ConditionCreateRequest;
import ru.med.app.dto.response.condition.ConditionResponse;
import ru.med.app.dto.response.condition.ConditionShortResponse;
import ru.med.app.entity.Condition;
import ru.med.app.entity.Doctor;
import ru.med.app.entity.User;
import ru.med.app.mapper.ConditionMapper;
import ru.med.app.repository.ConditionRepository;
import ru.med.app.repository.DoctorRepository;
import ru.med.app.repository.UserRepository;
import ru.med.app.service.ConditionService;
import ru.med.app.utils.JwtTokenUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConditionServiceImpl implements ConditionService {
    private final ConditionRepository conditionRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    public ConditionResponse createCondition(Authentication authentication, ConditionCreateRequest body) {
        User user = getUserByAuthentication(authentication);
        Doctor doctor = doctorRepository.findByLastNameAndFirstNameAndMiddleNameAndSpecialization(
                body.doctorLastName(),
                body.doctorFirstName(),
                body.doctorMiddleName(),
                body.doctorSpecialization()
        ).orElseThrow(() -> new UsernameNotFoundException("Doctor not found"));
        Doctor recorder = doctorRepository.findByLastNameAndFirstNameAndMiddleNameAndSpecialization(
                body.recorderLastName(),
                body.recorderFirstName(),
                body.recorderMiddleName(),
                body.recorderSpecialization()
        ).orElseThrow(() -> new UsernameNotFoundException("Doctor not found"));
        Condition condition = ConditionMapper.mapConditionCreateRequestToCondition(body, user, doctor, recorder);

        conditionRepository.save(condition);

        return ConditionMapper.mapConditionToResponse(condition);
    }

    @Override
    public ConditionResponse getConditionResponse(Authentication authentication) {
        User user = getUserByAuthentication(authentication);
        Condition condition = conditionRepository.findByPatient(user).orElseThrow(() -> new IllegalArgumentException("Condition not found"));
        return ConditionMapper.mapConditionToResponse(condition);
    }

    @Override
    public ConditionShortResponse getConditionShortResponse(UUID conditionId) {
        Condition condition = conditionRepository.findById(conditionId).orElseThrow(() -> new IllegalArgumentException("Condition not found"));
        return ConditionMapper.mapConditionToShortResponse(condition);
    }

    private User getUserByAuthentication(Authentication authentication) {
        UUID userId = jwtTokenUtils.getUserIdFromAuthentication(authentication);
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
