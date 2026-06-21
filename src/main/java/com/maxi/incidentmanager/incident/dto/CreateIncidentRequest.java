package com.maxi.incidentmanager.incident.dto;

import com.maxi.incidentmanager.incident.entity.Severity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CreateIncidentRequest(
        @NotBlank String title,
        @NotBlank String description,
        @NotNull Long businessServiceId,
        @NotNull Severity severity
) {
}
