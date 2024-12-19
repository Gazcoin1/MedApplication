package ru.med.app.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.med.app.entity.Condition;
import ru.med.app.entity.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, UUID> {
    Optional<Condition> findByPatient(@NotNull User patient);
}
