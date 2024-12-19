package ru.med.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.med.app.dto.request.ProcedureCreateRequest;
import ru.med.app.dto.response.procedure.ProcedureResponse;
import ru.med.app.dto.response.procedure.ProcedureShortResponse;
import ru.med.app.entity.Doctor;
import ru.med.app.entity.Procedure;
import ru.med.app.entity.User;
import ru.med.app.mapper.ProcedureMapper;
import ru.med.app.repository.DoctorRepository;
import ru.med.app.repository.ProcedureRepository;
import ru.med.app.repository.UserRepository;
import ru.med.app.service.ProcedureService;
import ru.med.app.utils.JwtTokenUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProcedureServiceImpl implements ProcedureService {
    private final ProcedureRepository procedureRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    public ProcedureResponse createProcedure(Authentication authentication, ProcedureCreateRequest body) {
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
        Procedure procedure = ProcedureMapper.mapProcedureCreateRequestToProcedure(body, user, doctor, recorder);

        procedureRepository.save(procedure);

        return ProcedureMapper.mapProcedureToResponse(procedure);
    }

    @Override
    public ProcedureResponse getProcedureResponse(Authentication authentication) {
        User user = getUserByAuthentication(authentication);
        Procedure procedure = procedureRepository.findByPatient(user).orElseThrow(() -> new UsernameNotFoundException("Procedure not found"));
        return ProcedureMapper.mapProcedureToResponse(procedure);
    }

    @Override
    public ProcedureShortResponse getProcedureShortResponse(UUID procedureId) {
        Procedure procedure = procedureRepository.findById(procedureId).orElseThrow(() -> new UsernameNotFoundException("Procedure not found"));
        return ProcedureMapper.mapProcedureToShortResponse(procedure);
    }

    private User getUserByAuthentication(Authentication authentication) {
        UUID userId = jwtTokenUtils.getUserIdFromAuthentication(authentication);
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
