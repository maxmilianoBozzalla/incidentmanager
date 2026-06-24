package com.maxi.incidentmanager.analysis.service;

import com.maxi.incidentmanager.analysis.constants.SeverityScoreRanges;
import com.maxi.incidentmanager.incident.entity.Incident;
import com.maxi.incidentmanager.incident.entity.Severity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeverityCalculatorImp implements SeverityCalculator {

    private final SeverityScoreCalculatorImp severityScoreCalculator;

    @Override
    public Severity calculateSeverity(Incident incident) {

        int scoreIncident = severityScoreCalculator.calculateScore(incident);

        if (scoreIncident <= SeverityScoreRanges.LOW_MAX_SCORE) {
            return Severity.LOW;

        } else if (scoreIncident <= SeverityScoreRanges.MEDIUM_MAX_SCORE) {
            return Severity.MEDIUM;

        } else if (scoreIncident <= SeverityScoreRanges.HIGH_MAX_SCORE) {
            return Severity.HIGH;
        }

        return Severity.CRITICAL;
    }
}
