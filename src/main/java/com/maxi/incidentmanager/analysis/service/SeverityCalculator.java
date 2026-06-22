package com.maxi.incidentmanager.analysis.service;

import com.maxi.incidentmanager.incident.entity.Incident;
import com.maxi.incidentmanager.incident.entity.Severity;

public interface SeverityCalculator {

    Severity calculateSeverity(Incident incident);

}
