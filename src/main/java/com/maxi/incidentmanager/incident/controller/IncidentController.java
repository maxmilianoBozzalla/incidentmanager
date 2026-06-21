package com.maxi.incidentmanager.incident.controller;

import com.maxi.incidentmanager.analysis.dto.AnalysisResponse;
import com.maxi.incidentmanager.history.dto.IncidentHistoryResponse;
import com.maxi.incidentmanager.incident.dto.CreateIncidentRequest;
import com.maxi.incidentmanager.incident.dto.IncidentResponse;
import com.maxi.incidentmanager.incident.dto.UpdateIncidentStatusRequest;
import com.maxi.incidentmanager.incident.service.IncidentServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/incidents")
public class IncidentController {

    private final IncidentServiceImp incidentService;

    /*
     * ------ CREATE ------
     * */
    @PostMapping
    public ResponseEntity<IncidentResponse> create(@Valid @RequestBody CreateIncidentRequest request) {

        IncidentResponse incident = incidentService.create(request);

        return ResponseEntity.created(URI.create("/api/v1/incidents/" + incident.id())).body(incident);
    }

    /*
     * ------ GET ------
     * */
    @GetMapping
    public ResponseEntity<List<IncidentResponse>> getAll() {
        return ResponseEntity.ok(incidentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidentResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(incidentService.getById(id));
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<List<IncidentHistoryResponse>> getAllIncidentHistory(@PathVariable UUID id) {
        return ResponseEntity.ok(incidentService.getAllIncidentHistory(id));
    }

    @GetMapping("/{id}/analysis")
    public ResponseEntity<AnalysisResponse> getAnalysisIncident(@PathVariable UUID id){
        return ResponseEntity.ok(incidentService.getAnalysisIncident(id));
    }
    /*
     * ------ UPDATE ------
     * */
    @PatchMapping("/id/status")
    public ResponseEntity<Void> changeStatus(@RequestBody UpdateIncidentStatusRequest request) {

        incidentService.changeStatus(request);

        return ResponseEntity.ok().build();
    }

}
