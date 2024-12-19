package ru.med.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.med.app.dto.request.AllergyCreateRequest;
import ru.med.app.dto.response.allergy.AllergyResponse;
import ru.med.app.dto.response.allergy.AllergyShortResponse;
import ru.med.app.entity.Allergy;
import ru.med.app.entity.Doctor;
import ru.med.app.entity.User;
import ru.med.app.mapper.AllergyMapper;
import ru.med.app.repository.AllergyRepository;
import ru.med.app.repository.DoctorRepository;
import ru.med.app.repository.UserRepository;
import ru.med.app.service.AllergyService;
import ru.med.app.utils.JwtTokenUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AllergyServiceImpl implements AllergyService {
    private final AllergyRepository allergyRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    public AllergyResponse createAllergy(Authentication authentication, AllergyCreateRequest body) {
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
        Allergy allergy = AllergyMapper.mapAllergyCreateRequestToAllergy(body, user, doctor, recorder);

        allergyRepository.save(allergy);

        return AllergyMapper.mapAllergyToResponse(allergy);
    }

    @Override
    public AllergyResponse getAllergyResponse(Authentication authentication) {
        User user = getUserByAuthentication(authentication);
        Allergy allergy = allergyRepository.findByPatient(user).orElseThrow(() -> new IllegalArgumentException("Allergy not found"));;
        return AllergyMapper.mapAllergyToResponse(allergy);
    }

    @Override
    public AllergyShortResponse getAllergyShortResponse(UUID conditionId) {
        Allergy allergy = allergyRepository.findById(conditionId).orElseThrow(() -> new IllegalArgumentException("Allergy not found"));
        return AllergyMapper.mapAllergyToShortResponse(allergy);
    }

    private User getUserByAuthentication(Authentication authentication) {
        UUID userId = jwtTokenUtils.getUserIdFromAuthentication(authentication);
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
