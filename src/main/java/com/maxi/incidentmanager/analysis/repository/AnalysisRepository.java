package com.maxi.incidentmanager.analysis.repository;

import com.maxi.incidentmanager.analysis.entity.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis,Long> {

    Analysis findByIncident_Id(UUID incidentId);

}
