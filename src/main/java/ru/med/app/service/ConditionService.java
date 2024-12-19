package ru.med.app.service;

import org.springframework.security.core.Authentication;
import ru.med.app.dto.request.ConditionCreateRequest;
import ru.med.app.dto.response.condition.ConditionResponse;
import ru.med.app.dto.response.condition.ConditionShortResponse;

import java.util.UUID;

public interface ConditionService {
    ConditionResponse createCondition(Authentication authentication, ConditionCreateRequest body);
    ConditionResponse getConditionResponse(Authentication authentication);
    ConditionShortResponse getConditionShortResponse(UUID conditionId);
}
