package com.maxi.incidentmanager.analysis;

import com.maxi.incidentmanager.analysis.service.SeverityCalculatorImp;
import com.maxi.incidentmanager.analysis.service.SeverityScoreCalculatorImp;
import com.maxi.incidentmanager.incident.entity.Incident;
import com.maxi.incidentmanager.incident.entity.Severity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SeverityCalculatorTest {

    @Mock
    private SeverityScoreCalculatorImp severityScoreCalculator;

    @InjectMocks
    private SeverityCalculatorImp severityCalculator;

    @Test
    void shouldReturnLowSeverityWhenScoreIs20() {

        when(severityScoreCalculator.calculateScore(any())).thenReturn(20);

        Severity severity = severityCalculator.calculateSeverity(
                Incident.builder().build());

        assertEquals(Severity.LOW, severity);
    }
    @Test
    void shouldReturnLowSeverityWhenScoreIs40() {

        when(severityScoreCalculator.calculateScore(any())).thenReturn(40);

        Severity severity = severityCalculator.calculateSeverity(
                Incident.builder().build());

        assertEquals(Severity.MEDIUM, severity);
    }

    @Test
    void shouldReturnLowSeverityWhenScoreIs65() {

        when(severityScoreCalculator.calculateScore(any())).thenReturn(65);

        Severity severity = severityCalculator.calculateSeverity(
                Incident.builder().build());

        assertEquals(Severity.HIGH, severity);
    }

    @Test
    void shouldReturnLowSeverityWhenScoreIs90() {

        when(severityScoreCalculator.calculateScore(any())).thenReturn(90);

        Severity severity = severityCalculator.calculateSeverity(
                Incident.builder().build());

        assertEquals(Severity.CRITICAL, severity);
    }


}
