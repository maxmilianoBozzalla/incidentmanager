package com.maxi.incidentmanager.incident.dto;

import com.maxi.incidentmanager.incident.entity.Severity;
import com.maxi.incidentmanager.incident.entity.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record IncidentResponse(
        UUID id,
        String title,
        String description,
        Severity severity,
        String application,
        BigDecimal errorRate,
        Long requestCount,
        Long avgResponseTime,
        Integer impactedUsers,
        String service,
        Status status,
        LocalDateTime createdAt
) {
}
