package com.maxi.incidentmanager.service.repository;

import com.maxi.incidentmanager.service.entity.BusinessService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessServiceRepository extends JpaRepository<BusinessService,Long> {

}
