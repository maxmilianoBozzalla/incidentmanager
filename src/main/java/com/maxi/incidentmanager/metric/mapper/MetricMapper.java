package com.maxi.incidentmanager.metric.mapper;

import com.maxi.incidentmanager.metric.dto.MetricRequest;
import com.maxi.incidentmanager.metric.dto.MetricResponse;
import com.maxi.incidentmanager.metric.entity.Metric;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MetricMapper {

    Metric toEntity(MetricRequest metricRequest);

    MetricResponse toDTOMetricResponse(Metric metric);

    List<MetricResponse> toDTOMetricResponseList(List<Metric> metrics);

}
