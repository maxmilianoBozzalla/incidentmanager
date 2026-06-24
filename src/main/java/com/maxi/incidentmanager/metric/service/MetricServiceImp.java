package com.maxi.incidentmanager.metric.service;

import com.maxi.incidentmanager.metric.dto.MetricRequest;
import com.maxi.incidentmanager.metric.dto.MetricResponse;
import com.maxi.incidentmanager.metric.entity.Metric;
import com.maxi.incidentmanager.metric.mapper.MetricMapper;
import com.maxi.incidentmanager.metric.repository.MetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MetricServiceImp implements MetricService{

    private final MetricRepository metricRepository;
    private final MetricMapper metricMapper;

    @Override
    public MetricResponse create(MetricRequest metricRequest) {

        Metric metric = metricMapper.toEntity(metricRequest);

        metricRepository.save(metric);

        return metricMapper.toDTOMetricResponse(metric);
    }

    @Override
    public List<MetricResponse> findAll() {

        List<Metric> metrics = metricRepository.findAll();

        return metricMapper.toDTOMetricResponseList(metrics);
    }
}
