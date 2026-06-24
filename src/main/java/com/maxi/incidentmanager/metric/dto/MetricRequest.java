package com.maxi.incidentmanager.metric.dto;

import java.math.BigDecimal;

public record MetricRequest(
        String hostName,
        BigDecimal cpuUsage,
        BigDecimal memoryUsage,
        BigDecimal diskUsage
) {
}
