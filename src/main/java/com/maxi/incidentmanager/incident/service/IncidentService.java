package com.maxi.incidentmanager.incident.service;

import com.maxi.incidentmanager.analysis.dto.AnalysisResponse;
import com.maxi.incidentmanager.incident.dto.CreateIncidentRequest;
import com.maxi.incidentmanager.history.dto.IncidentHistoryResponse;
import com.maxi.incidentmanager.incident.dto.IncidentResponse;
import com.maxi.incidentmanager.incident.dto.UpdateIncidentStatusRequest;

import java.util.List;
import java.util.UUID;

public interface IncidentService {

    IncidentResponse create(CreateIncidentRequest incident);

    IncidentResponse getById(UUID id);

    List<IncidentResponse> getAll();

    void changeStatus(UpdateIncidentStatusRequest request);

    List<IncidentHistoryResponse> getAllIncidentHistory(UUID id);

    AnalysisResponse getAnalysisIncident(UUID id);

}
