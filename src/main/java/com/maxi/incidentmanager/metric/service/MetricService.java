package com.maxi.incidentmanager.metric.service;

import com.maxi.incidentmanager.metric.dto.MetricRequest;
import com.maxi.incidentmanager.metric.dto.MetricResponse;

import java.util.List;

public interface MetricService {

    MetricResponse create(MetricRequest metricRequest);

    List<MetricResponse> findAll();
}
