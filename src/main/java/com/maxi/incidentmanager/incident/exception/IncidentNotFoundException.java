package com.maxi.incidentmanager.incident.exception;

import com.maxi.incidentmanager.shared.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class IncidentNotFoundException extends BusinessException {
    public IncidentNotFoundException() {
        super("Incident not found", HttpStatus.NOT_FOUND);
    }
}
