package com.maxi.incidentmanager.analysis;

import com.maxi.incidentmanager.analysis.service.ConfidenceScoreCalculatorImp;
import com.maxi.incidentmanager.incident.entity.Incident;
import com.maxi.incidentmanager.incident.entity.Severity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfidenceScoreCalculatorImpTest {

    private final ConfidenceScoreCalculatorImp calculator =
            new ConfidenceScoreCalculatorImp();

    @Test
    void shouldCalculateConfidenceScoreForLowSeverityIncident() {

        Incident incident = Incident.builder()
                .severity(Severity.LOW)
                .impactedUsers(2)
                .build();

        int score = calculator.confidenceCalculate(incident, false);

        assertEquals(15, score);
    }

    @Test
    void shouldCalculateConfidenceScoreForMediumSeverityIncident() {

        Incident incident = Incident.builder()
                .severity(Severity.MEDIUM)
                .impactedUsers(50)
                .build();

        int score = calculator.confidenceCalculate(incident, false);

        assertEquals(30, score);
    }

    @Test
    void shouldCalculateConfidenceScoreForHighSeverityRecurringIncident() {

        Incident incident = Incident.builder()
                .severity(Severity.HIGH)
                .impactedUsers(150)
                .build();

        int score = calculator.confidenceCalculate(incident, true);

        assertEquals(80, score);
    }

    @Test
    void shouldCalculateConfidenceScoreForCriticalRecurringIncident() {

        Incident incident = Incident.builder()
                .severity(Severity.CRITICAL)
                .impactedUsers(5000)
                .build();

        int score = calculator.confidenceCalculate(
                incident,
                true
        );

        assertEquals(100, score);
    }


}
