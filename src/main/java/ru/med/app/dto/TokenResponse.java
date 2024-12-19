package ru.med.app.dto;

import lombok.Builder;

@Builder
public record TokenResponse(String token) {

}
