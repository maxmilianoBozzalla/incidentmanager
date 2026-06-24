package com.maxi.incidentmanager.metric.repository;

import com.maxi.incidentmanager.metric.entity.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends JpaRepository<Metric,Long> {
}
