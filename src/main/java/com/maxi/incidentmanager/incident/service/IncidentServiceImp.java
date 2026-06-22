package com.maxi.incidentmanager.incident.service;

import com.maxi.incidentmanager.analysis.dto.AnalysisResponse;
import com.maxi.incidentmanager.analysis.entity.Analysis;
import com.maxi.incidentmanager.analysis.mapper.AnalysisMapper;
import com.maxi.incidentmanager.analysis.repository.AnalysisRepository;
import com.maxi.incidentmanager.analysis.service.AnalysisServiceImp;
import com.maxi.incidentmanager.analysis.service.SeverityCalculatorImp;
import com.maxi.incidentmanager.history.mapper.IncidentHistoryMapper;
import com.maxi.incidentmanager.incident.dto.CreateIncidentRequest;
import com.maxi.incidentmanager.history.dto.IncidentHistoryResponse;
import com.maxi.incidentmanager.incident.dto.IncidentResponse;
import com.maxi.incidentmanager.incident.dto.UpdateIncidentStatusRequest;
import com.maxi.incidentmanager.incident.entity.Incident;
import com.maxi.incidentmanager.incident.entity.Status;
import com.maxi.incidentmanager.incident.exception.IncidentNotFoundException;
import com.maxi.incidentmanager.incident.mapper.IncidentMapper;
import com.maxi.incidentmanager.incident.repository.IncidentRepository;
import com.maxi.incidentmanager.history.entity.IncidentHistory;
import com.maxi.incidentmanager.history.repository.IncidentHistoryRepository;
import com.maxi.incidentmanager.service.entity.BusinessService;
import com.maxi.incidentmanager.service.exception.BusinessServiceNotFoundException;
import com.maxi.incidentmanager.service.repository.BusinessServiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class IncidentServiceImp implements IncidentService {

    private final IncidentRepository incidentRepository;
    private final IncidentHistoryRepository incidentHistoryRepository;
    private final BusinessServiceRepository businessServiceRepository;
    private final AnalysisRepository analysisRepository;
    private final IncidentHistoryMapper incidentHistoryMapper;
    private final IncidentMapper mapper;
    private final AnalysisMapper analysisMapper;
    private final AnalysisServiceImp analysisService;
    private final SeverityCalculatorImp severityCalculator;

    @Override
    public IncidentResponse create(CreateIncidentRequest incident) {

        BusinessService businessService = businessServiceRepository.findById(incident.businessServiceId())
                .orElseThrow(BusinessServiceNotFoundException::new);

        Incident newIncident = mapper.toEntity(incident);
        newIncident.setBusinessService(businessService);
        newIncident.setSeverity(severityCalculator.calculateSeverity(newIncident));

        incidentRepository.save(newIncident);
        analysisService.analyze(newIncident);


        return mapper.toDTOResponse(newIncident);
    }

    @Override
    public IncidentResponse getById(UUID id) {

        Incident incident = incidentRepository.findById(id)
                .orElseThrow(IncidentNotFoundException::new);

        return mapper.toDTOResponse(incident);
    }

    @Override
    public List<IncidentResponse> getAll() {
        List<Incident> listIncidents = incidentRepository.findAll();
        return mapper.toDTOResponseList(listIncidents);
    }

    @Transactional
    @Override
    public void changeStatus(UpdateIncidentStatusRequest request) {

        Incident incident = incidentRepository.findById(request.id())
                .orElseThrow(IncidentNotFoundException::new);

        Status previousStatus = incident.getStatus();

        incident.setStatus(request.status());

        incidentRepository.save(incident);

        incidentHistoryRepository.save(
                IncidentHistory.builder()
                        .incident(incident)
                        .previousStatus(previousStatus)
                        .newStatus(request.status()).build());

    }

    @Override
    public List<IncidentHistoryResponse> getAllIncidentHistory(UUID id) {

        List<IncidentHistory> incidentHistories = incidentHistoryRepository.findByIncidentId(id);

        return incidentHistoryMapper.toDTOIncidentHistoryList(incidentHistories);
    }

    @Override
    public AnalysisResponse getAnalysisIncident(UUID id) {

        Analysis analysis = analysisRepository.findByIncident_Id(id);

        if(analysis == null) {
            throw  new RuntimeException("Analysis null");
        }

        return analysisMapper.toDTOResponse(analysis);
    }
}
