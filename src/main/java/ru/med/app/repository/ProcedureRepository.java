package ru.med.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.med.app.entity.Procedure;
import ru.med.app.entity.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, UUID> {
    Optional<Procedure> findByPatient(User user);
}
