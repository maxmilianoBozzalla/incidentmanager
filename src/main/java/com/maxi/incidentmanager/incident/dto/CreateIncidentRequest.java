package com.maxi.incidentmanager.incident.dto;

import com.maxi.incidentmanager.incident.entity.Severity;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;


public record CreateIncidentRequest(
        @NotBlank String title,
        @NotBlank String description,
        @NotNull Long businessServiceId,
        @NotNull @DecimalMin("0.0") @DecimalMax("100.0") BigDecimal errorRate,
        @NotNull @PositiveOrZero Long requestCount,
        @NotNull @PositiveOrZero Long avgResponseTime,
        @NotNull @PositiveOrZero Integer impactedUsers
) {
}
