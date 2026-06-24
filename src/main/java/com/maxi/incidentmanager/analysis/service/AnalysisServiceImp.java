package com.maxi.incidentmanager.analysis.service;

import com.maxi.incidentmanager.analysis.dto.AnalysisResponse;
import com.maxi.incidentmanager.analysis.entity.Analysis;
import com.maxi.incidentmanager.analysis.entity.AnalysisResult;
import com.maxi.incidentmanager.analysis.mapper.AnalysisMapper;
import com.maxi.incidentmanager.analysis.repository.AnalysisRepository;
import com.maxi.incidentmanager.incident.entity.Incident;
import com.maxi.incidentmanager.incident.entity.Severity;
import com.maxi.incidentmanager.incident.repository.IncidentRepository;
import com.maxi.incidentmanager.analysis.constants.AnalysisConstants;
import com.maxi.incidentmanager.analysis.constants.AnalysisReasons;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnalysisServiceImp implements AnalysisService {

    private final ConfidenceScoreCalculatorImp confidenceScoreCalculator;
    private final IncidentRepository incidentRepository;
    private final AnalysisRepository analysisRepository;
    private final AnalysisMapper analysisMapper;

    @Override
    public AnalysisResponse analyze(Incident incident) {

        AnalysisResult analysisResult;
        Long idService = incident.getBusinessService().getId();
        boolean isRecurrentService = isRecurringIncident(idService);
        String reason = "";

        if (isHighImpactIncident(incident)) {
            analysisResult = AnalysisResult.HIGH_IMPACT_INCIDENT;
            reason = AnalysisReasons.HIGH_IMPACT_INCIDENT_REASON;

        } else if (isRecurrentService) {
            analysisResult = AnalysisResult.RECURRING_INCIDENT;
            reason = AnalysisReasons.RECURRING_INCIDENT_REASON;

        } else if (isFalsePositive(incident)) {
            analysisResult = AnalysisResult.FALSE_POSITIVE;
            reason = AnalysisReasons.FALSE_POSITIVE_REASON;

        } else {
            analysisResult = AnalysisResult.NEW_INCIDENT;
            reason = AnalysisReasons.NEW_INCIDENT_REASON;
        }

        int scoreConfidence = confidenceScoreCalculator.confidenceCalculate(incident,
                isRecurrentService);

        Analysis analysis = Analysis.builder()
                .incident(incident)
                .analysisResult(analysisResult)
                .reason(reason)
                .confidenceScore(scoreConfidence)
                .build();

        analysisRepository.save(analysis);


        return analysisMapper.toDTOResponse(analysis);
    }

    @Override
    public boolean isRecurringIncident(Long serviceId) {

        LocalDateTime rangeDays = LocalDateTime.now().minusDays(AnalysisConstants.DAYS_TO_ANALYZE);
        Long totalIncidents = incidentRepository.countByBusinessServiceIdAndCreatedAtAfter(serviceId, rangeDays);

        return totalIncidents >= AnalysisConstants.RECURRING_INCIDENT_THRESHOLD;
    }

    @Override
    public boolean isHighImpactIncident(Incident incident) {
        return incident.getSeverity() == Severity.HIGH || incident.getSeverity() == Severity.CRITICAL;
    }

    @Override
    public boolean isFalsePositive(Incident incident) {
        return incident.getSeverity() == Severity.LOW && incident.getImpactedUsers() < 5;
    }
}
