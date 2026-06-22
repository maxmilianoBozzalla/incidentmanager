package com.maxi.incidentmanager.analysis.service;

import com.maxi.incidentmanager.incident.entity.Incident;

public interface SeverityScoreCalculator {

    int calculateScore(Incident incident);
}
