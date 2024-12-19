package ru.med.app.service;

import ru.med.app.dto.request.DoctorCreateRequest;
import ru.med.app.dto.response.doctor.DoctorResponse;
import ru.med.app.dto.response.doctor.DoctorShortResponse;

import java.util.UUID;

public interface DoctorService {
    DoctorResponse getDoctorResponse(UUID doctorId);
    DoctorShortResponse getDoctorShortResponse(UUID doctorId);
    DoctorResponse createDoctor(DoctorCreateRequest body);
}
