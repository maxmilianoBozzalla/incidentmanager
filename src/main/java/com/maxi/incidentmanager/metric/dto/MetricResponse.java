package com.maxi.incidentmanager.metric.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MetricResponse(
        Long id,
        String hostName,
        BigDecimal cpuUsage,
        BigDecimal memoryUsage,
        BigDecimal diskUsage,
        LocalDateTime createdAt
) {
}
