package ru.med.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.med.app.dto.request.DoctorCreateRequest;
import ru.med.app.dto.response.doctor.DoctorResponse;
import ru.med.app.dto.response.doctor.DoctorShortResponse;
import ru.med.app.entity.Doctor;
import ru.med.app.mapper.DoctorMapper;
import ru.med.app.repository.DoctorRepository;
import ru.med.app.service.DoctorService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    //TODO СОЗДАЙ ПОТОМ ИСКЛЮЧКЕНИЯ
    @Override
    public DoctorResponse getDoctorResponse(UUID doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new UsernameNotFoundException("Doctor not found"));
        return DoctorMapper.mapDoctorToResponse(doctor);
    }

    @Override
    public DoctorShortResponse getDoctorShortResponse(UUID doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new UsernameNotFoundException("Doctor not found"));
        return DoctorMapper.mapDoctorToShortResponse(doctor);
    }

    @Override
    public DoctorResponse createDoctor(DoctorCreateRequest body) {
        Doctor doctor = DoctorMapper.mapDoctorCreateRequestToDoctor(body);
        log.info("Doctor created: {}", doctor);
        isDoctorCreated(doctor);

        doctorRepository.save(doctor);

        return DoctorMapper.mapDoctorToResponse(doctor);
    }

    private void isDoctorCreated(Doctor doctor) {
        if (doctor.getFirstName() == null) {
            throw new UsernameNotFoundException("Doctor is not created");
        }
    }

}
