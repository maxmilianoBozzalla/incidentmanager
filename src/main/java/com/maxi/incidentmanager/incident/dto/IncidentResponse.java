package com.maxi.incidentmanager.incident.dto;

import com.maxi.incidentmanager.incident.entity.Severity;
import com.maxi.incidentmanager.incident.entity.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public record IncidentResponse(
        UUID id,
        String title,
        String description,
        Severity severity,
        String application,
        String service,
        Status status,
        LocalDateTime createdAt
) {
}
