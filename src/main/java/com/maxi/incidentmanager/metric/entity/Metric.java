package com.maxi.incidentmanager.metric.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "metrics")
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "host_name", nullable = false, length = 200)
    private String hostName;

    @Column(name = "cpu_usage",precision = 5, scale = 2,  nullable = false)
    private BigDecimal cpuUsage;
    @Column(name = "memory_usage",precision = 5, scale = 2,  nullable = false)
    private BigDecimal memoryUsage;
    @Column(name = "disk_usage",precision = 5, scale = 2,  nullable = false)
    private BigDecimal diskUsage;

    @CreationTimestamp
    @Column(name="created_at",nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
