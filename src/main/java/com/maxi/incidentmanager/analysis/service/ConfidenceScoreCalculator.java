package com.maxi.incidentmanager.analysis.service;

import com.maxi.incidentmanager.incident.entity.Incident;

public interface ConfidenceScoreCalculator {

    int confidenceCalculate(Incident incident, boolean isRecurringIncident);

}
