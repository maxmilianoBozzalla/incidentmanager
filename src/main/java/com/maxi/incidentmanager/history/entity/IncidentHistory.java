package com.maxi.incidentmanager.history.entity;

import com.maxi.incidentmanager.incident.entity.Incident;
import com.maxi.incidentmanager.incident.entity.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "incident_history")
public class IncidentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "incident_id")
    private Incident incident;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status previousStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status newStatus;

    @CreationTimestamp
    @Column(name="created_at",nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
