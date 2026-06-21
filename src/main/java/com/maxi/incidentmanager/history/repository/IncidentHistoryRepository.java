package com.maxi.incidentmanager.history.repository;

import com.maxi.incidentmanager.history.entity.IncidentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IncidentHistoryRepository extends JpaRepository<IncidentHistory, UUID> {

    List<IncidentHistory> findByIncidentId(UUID incidentId);

}
