package com.maxi.incidentmanager.analysis.entity;

import com.maxi.incidentmanager.incident.entity.Incident;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "analysis")
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "incident_id",nullable = false)
    private Incident incident;

    @Column(name = "analysis_result", nullable = false)
    @Enumerated(EnumType.STRING)
    private AnalysisResult analysisResult;

    private String reason;

    @Column(name = "confidence_score", nullable = false)
    private int confidenceScore;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


}
