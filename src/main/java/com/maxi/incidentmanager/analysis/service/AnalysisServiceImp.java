package com.maxi.incidentmanager.analysis.service;

import com.maxi.incidentmanager.analysis.dto.AnalysisResponse;
import com.maxi.incidentmanager.analysis.entity.Analysis;
import com.maxi.incidentmanager.analysis.entity.AnalysisResult;
import com.maxi.incidentmanager.analysis.mapper.AnalysisMapper;
import com.maxi.incidentmanager.analysis.repository.AnalysisRepository;
import com.maxi.incidentmanager.incident.entity.Incident;
import com.maxi.incidentmanager.incident.repository.IncidentRepository;
import com.maxi.incidentmanager.service.entity.BusinessService;
import com.maxi.incidentmanager.service.exception.BusinessServiceNotFoundException;
import com.maxi.incidentmanager.service.repository.BusinessServiceRepository;
import com.maxi.incidentmanager.analysis.constants.AnalysisConstants;
import com.maxi.incidentmanager.analysis.constants.AnalysisReasons;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnalysisServiceImp implements AnalysisService {

    private final BusinessServiceRepository businessServiceRepository;
    private final IncidentRepository incidentRepository;
    private final AnalysisRepository analysisRepository;
    private final AnalysisMapper analysisMapper;

    @Override
    public AnalysisResponse analyze(Incident incident) {

        AnalysisResult analysisResult = evaluateRecurringIncident(incident.getBusinessService().getId());
        String reason = "";

        switch (analysisResult) {
            case NEW_INCIDENT -> reason = AnalysisReasons.NEW_INCIDENT_REASON;
            case RECURRING_INCIDENT -> reason = AnalysisReasons.RECURRING_INCIDENT_REASON;
        }

        Analysis analysis = Analysis.builder()
                .incident(incident)
                .analysisResult(analysisResult)
                .reason(reason).build();

        analysisRepository.save(analysis);


        return analysisMapper.toDTOResponse(analysis);
    }

    @Override
    public AnalysisResult evaluateRecurringIncident(Long serviceId) {

        BusinessService businessService =  businessServiceRepository.findById(serviceId)
                .orElseThrow(BusinessServiceNotFoundException::new);

        LocalDateTime rangeDays = LocalDateTime.now().minusDays(AnalysisConstants.DAYS_TO_ANALYZE);
        Long totalIncidents = incidentRepository.countByBusinessServiceIdAndCreatedAtAfter(serviceId,rangeDays);

        if(totalIncidents >= AnalysisConstants.RECURRING_INCIDENT_THRESHOLD){
            return AnalysisResult.RECURRING_INCIDENT;
        }

        return AnalysisResult.NEW_INCIDENT;
    }
}
