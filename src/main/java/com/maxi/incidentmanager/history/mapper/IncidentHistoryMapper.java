package com.maxi.incidentmanager.history.mapper;

import com.maxi.incidentmanager.history.dto.IncidentHistoryResponse;
import com.maxi.incidentmanager.history.entity.IncidentHistory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IncidentHistoryMapper {
    List<IncidentHistoryResponse> toDTOIncidentHistoryList(List<IncidentHistory> incidentHistories);
}
