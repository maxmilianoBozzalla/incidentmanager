package com.maxi.incidentmanager.analysis;

import com.maxi.incidentmanager.analysis.dto.AnalysisResponse;
import com.maxi.incidentmanager.analysis.entity.Analysis;
import com.maxi.incidentmanager.analysis.entity.AnalysisResult;
import com.maxi.incidentmanager.analysis.mapper.AnalysisMapper;
import com.maxi.incidentmanager.analysis.service.AnalysisServiceImp;
import com.maxi.incidentmanager.analysis.service.ConfidenceScoreCalculatorImp;
import com.maxi.incidentmanager.service.entity.BusinessService;
import com.maxi.incidentmanager.incident.entity.Incident;
import com.maxi.incidentmanager.incident.entity.Severity;
import com.maxi.incidentmanager.incident.repository.IncidentRepository;
import com.maxi.incidentmanager.analysis.repository.AnalysisRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@ExtendWith(MockitoExtension.class)
class AnalysisServiceImpTest {

    @Mock
    private ConfidenceScoreCalculatorImp confidenceScoreCalculator;

    @Mock
    private IncidentRepository incidentRepository;

    @Mock
    private AnalysisRepository analysisRepository;

    @Mock
    private AnalysisMapper analysisMapper;

    @InjectMocks
    private AnalysisServiceImp analysisService;

    @Test
    void shouldCreateHighImpactAnalysis() {

        // Arrange
        Incident incident = Incident.builder()
                .severity(Severity.HIGH)
                .businessService(
                        BusinessService.builder()
                                .id(1L)
                                .build()
                )
                .build();

        when(confidenceScoreCalculator.confidenceCalculate(any(), anyBoolean())
        ).thenReturn(80);

        AnalysisResponse response = new AnalysisResponse(
                AnalysisResult.HIGH_IMPACT_INCIDENT,
                "High impact incident",
                80,
                LocalDateTime.now()
        );

        when(analysisMapper.toDTOResponse(any())).thenReturn(response);

        // Act
        AnalysisResponse result = analysisService.analyze(incident);

        // Assert
        assertEquals(AnalysisResult.HIGH_IMPACT_INCIDENT, result.analysisResult());

        assertEquals(80, result.confidenceScore());

        verify(analysisRepository)
                .save(any(Analysis.class));
    }



}
