package com.maxi.incidentmanager.analysis.service;

import com.maxi.incidentmanager.analysis.dto.AnalysisResponse;
import com.maxi.incidentmanager.analysis.entity.AnalysisResult;
import com.maxi.incidentmanager.incident.entity.Incident;

public interface AnalysisService {

    AnalysisResponse analyze(Incident incident);

    AnalysisResult evaluateRecurringIncident(Long serviceId);
}
