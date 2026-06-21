package com.maxi.incidentmanager.incident.mapper;

import com.maxi.incidentmanager.incident.dto.CreateIncidentRequest;
import com.maxi.incidentmanager.incident.dto.IncidentResponse;
import com.maxi.incidentmanager.incident.entity.Incident;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IncidentMapper {
    Incident toEntity(CreateIncidentRequest incidentRequest);

    @Mapping(target = "application", source = "businessService.application.name")
    @Mapping(target = "service", source = "businessService.name")
    IncidentResponse toDTOResponse(Incident incident);

    List<IncidentResponse> toDTOResponseList(List<Incident> incidents);
}
