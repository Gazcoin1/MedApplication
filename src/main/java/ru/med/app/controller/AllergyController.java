package ru.med.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.med.app.dto.request.AllergyCreateRequest;
import ru.med.app.dto.response.allergy.AllergyResponse;
import ru.med.app.dto.response.allergy.AllergyShortResponse;
import ru.med.app.service.AllergyService;

import java.util.UUID;

@RestController
@RequestMapping("med")
@RequiredArgsConstructor
public class AllergyController {
    private final AllergyService allergyService;

    @PostMapping("/allergy")
    public AllergyResponse createAllergy(Authentication authentication, @RequestBody @Valid AllergyCreateRequest body) {
        return allergyService.createAllergy(authentication, body);
    }

    @GetMapping("/allergy")
    public AllergyResponse getAllergy(Authentication authentication) {
        return allergyService.getAllergyResponse(authentication);
    }

    @GetMapping("/allergy/{allergyId}")
    public AllergyShortResponse getAllergyInfo(String allergyId) {
        UUID id = UUID.fromString(allergyId);
        return allergyService.getAllergyShortResponse(id);
    }
}
