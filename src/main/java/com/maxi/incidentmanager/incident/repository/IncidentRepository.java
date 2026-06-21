package com.maxi.incidentmanager.incident.repository;

import com.maxi.incidentmanager.incident.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, UUID> {
    Long countByBusinessServiceIdAndCreatedAtAfter(
            Long serviceId,
            LocalDateTime date
    );
}
