package com.maxi.incidentmanager.history.dto;

import com.maxi.incidentmanager.incident.entity.Status;

import java.time.LocalDateTime;

public record IncidentHistoryResponse(
        Status previousStatus,
        Status newStatus,
        LocalDateTime createdAt
) {
}
