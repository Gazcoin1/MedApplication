package ru.med.app.service;

import org.springframework.security.core.Authentication;
import ru.med.app.dto.request.AllergyCreateRequest;
import ru.med.app.dto.response.allergy.AllergyResponse;
import ru.med.app.dto.response.allergy.AllergyShortResponse;

import java.util.UUID;

public interface AllergyService {
    AllergyResponse createAllergy(Authentication authentication, AllergyCreateRequest body);
    AllergyResponse getAllergyResponse(Authentication authentication);
    AllergyShortResponse getAllergyShortResponse(UUID conditionId);
}
