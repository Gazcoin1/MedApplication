package ru.med.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.med.app.entity.Doctor;
import ru.med.app.entity.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    Optional<Doctor> findByLastNameAndFirstName(String lastName, String firstName);
    Optional<Doctor> findByLastNameAndFirstNameAndMiddleName(String lastName, String firstName, String middleName);
    Optional<Doctor> findByLastNameAndFirstNameAndMiddleNameAndSpecialization(String lastName, String firstName, String middleName, String specialization);
}
