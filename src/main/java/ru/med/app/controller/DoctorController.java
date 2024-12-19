package ru.med.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.med.app.dto.request.DoctorCreateRequest;
import ru.med.app.dto.response.doctor.DoctorResponse;
import ru.med.app.dto.response.doctor.DoctorShortResponse;
import ru.med.app.service.DoctorService;

import java.util.UUID;

@RestController
@RequestMapping("med")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping("/doctor")
    public DoctorResponse getDoctor(String doctorId) {
        UUID id = UUID.fromString(doctorId);
        return doctorService.getDoctorResponse(id);
    }

    @GetMapping("/doctor/{doctorId}")
    public DoctorShortResponse getDoctorNameById(@PathVariable String doctorId) {
        UUID id = UUID.fromString(doctorId);
        return doctorService.getDoctorShortResponse(id);
    }

    @PostMapping("/doctor")
    public DoctorResponse createDoctor(@RequestBody @Valid DoctorCreateRequest body) {
        return doctorService.createDoctor(body);
    }
}
