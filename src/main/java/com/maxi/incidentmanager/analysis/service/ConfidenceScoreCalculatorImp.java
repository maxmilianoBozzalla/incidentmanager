package com.maxi.incidentmanager.analysis.service;

import com.maxi.incidentmanager.analysis.constants.ConfidenceScoreThresholds;
import com.maxi.incidentmanager.incident.entity.Incident;
import com.maxi.incidentmanager.incident.entity.Severity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class ConfidenceScoreCalculatorImp implements ConfidenceScoreCalculator {


    @Override
    public int confidenceCalculate(Incident incident, boolean isRecurringIncident) {

        Severity severity = incident.getSeverity();

        int severityScore = scoreConfidenceSeverity(severity);
        int impactUserScore = scoreImpactedUsers(incident.getImpactedUsers());
        int recurringIncidentScore = scoreConfidenceRecurring(isRecurringIncident);



        return severityScore + impactUserScore + recurringIncidentScore;
    }

    private int scoreConfidenceSeverity(Severity severity) {

        int score = 0;

        if (severity == Severity.LOW) {
            score = ConfidenceScoreThresholds.LOW_SEVERITY_SCORE;

        } else if (severity == Severity.MEDIUM) {
            score = ConfidenceScoreThresholds.MEDIUM_SEVERITY_SCORE;

        } else if (severity == Severity.HIGH) {
            score = ConfidenceScoreThresholds.HIGH_SEVERITY_SCORE;

        } else {
            score = ConfidenceScoreThresholds.CRITICAL_SEVERITY_SCORE;
        }

        return score;
    }

    private int scoreImpactedUsers(int impactedUsers) {

        int score = 0;

        if (impactedUsers <= 10) {
            score = ConfidenceScoreThresholds.LOW_IMPACTED_USERS_SCORE;

        } else if (impactedUsers <= 100) {
            score = ConfidenceScoreThresholds.MEDIUM_IMPACTED_USERS_SCORE;

        } else if (impactedUsers <= 500) {
            score = ConfidenceScoreThresholds.HIGH_IMPACTED_USERS_SCORE;

        } else {
            score = ConfidenceScoreThresholds.CRITICAL_IMPACTED_USERS_SCORE;
        }

        return score;
    }

    public int scoreConfidenceRecurring(boolean isRecurrent) {

        int score = 0;
        if (isRecurrent) {
            score = ConfidenceScoreThresholds.RECURRING_INCIDENT_SCORE;
        }

        return score;
    }
}
