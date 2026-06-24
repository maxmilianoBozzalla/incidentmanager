package com.maxi.incidentmanager.metric.controller;

import com.maxi.incidentmanager.metric.dto.MetricRequest;
import com.maxi.incidentmanager.metric.dto.MetricResponse;
import com.maxi.incidentmanager.metric.service.MetricServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/metrics")
public class MetricController {

    private final MetricServiceImp metricService;

    /*
     * ------ CREATE ------
     * */
    @PostMapping
    public ResponseEntity<MetricResponse> create(@RequestBody MetricRequest metricRequest) {

        MetricResponse metric = metricService.create(metricRequest);

        return ResponseEntity.created(URI.create("/api/v1/metrics/" + metric.id())).body(metric);
    }

    /*
     * ------ GET ------
     * */
    @GetMapping
    public ResponseEntity<List<MetricResponse>> findAll(){
        return ResponseEntity.ok(metricService.findAll());
    }


}
