package com.maxi.incidentmanager.incident.entity;

import com.maxi.incidentmanager.service.entity.BusinessService;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "incidents")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id")
    private BusinessService businessService;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Severity severity;

    @Column(name = "error_rate",precision = 10, scale = 2,  nullable = false)
    private BigDecimal errorRate;

    @Column(name = "request_count", nullable = false)
    private Long requestCount;

    @Column(name = "response_time", nullable = false)
    private Long avgResponseTime;

    /**
     * Estimated number of affected users during the incident.
     */
    @Column(name = "impacted_user", nullable = false)
    private Integer impactedUsers;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Status status = Status.OPEN;

    @CreationTimestamp
    @Column(name="created_at",nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;
}
