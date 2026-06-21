package com.maxi.incidentmanager.incident.dto;

import com.maxi.incidentmanager.incident.entity.Status;

import java.util.UUID;

public record UpdateIncidentStatusRequest(
        UUID id,
        Status status

) {
}
