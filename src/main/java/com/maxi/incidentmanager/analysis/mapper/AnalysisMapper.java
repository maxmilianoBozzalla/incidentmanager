package com.maxi.incidentmanager.analysis.mapper;

import com.maxi.incidentmanager.analysis.dto.AnalysisResponse;
import com.maxi.incidentmanager.analysis.entity.Analysis;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnalysisMapper {

    AnalysisResponse toDTOResponse(Analysis analysis);

}
