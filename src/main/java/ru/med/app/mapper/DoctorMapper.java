package ru.med.app.mapper;

import org.springframework.stereotype.Component;
import ru.med.app.dto.request.DoctorCreateRequest;
import ru.med.app.dto.response.doctor.DoctorResponse;
import ru.med.app.dto.response.doctor.DoctorShortResponse;
import ru.med.app.entity.Doctor;

@Component
public class DoctorMapper {
    public static DoctorResponse mapDoctorToResponse(Doctor doctor) {
        return new DoctorResponse(
                doctor.getId().toString(),
                doctor.getLastName(),
                doctor.getFirstName(),
                doctor.getMiddleName(),
                doctor.getSpecialization()
        );
    }

    public static DoctorShortResponse mapDoctorToShortResponse(Doctor doctor) {
        return new DoctorShortResponse(
                doctor.getLastName(),
                doctor.getFirstName(),
                doctor.getMiddleName()
        );
    }

    public static Doctor mapDoctorCreateRequestToDoctor(DoctorCreateRequest body) {
        Doctor doctor = new Doctor();
        doctor.setLastName(body.lastName());
        doctor.setFirstName(body.firstName());
        doctor.setMiddleName(body.middleName());
        doctor.setSpecialization(body.specialization());
        return doctor;
    }
}
