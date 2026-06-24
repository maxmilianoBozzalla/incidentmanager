package com.maxi.incidentmanager.analysis.dto;

import com.maxi.incidentmanager.analysis.entity.AnalysisResult;

import java.time.LocalDateTime;

public record AnalysisResponse(
        AnalysisResult analysisResult,
        String reason,
        int confidenceScore,
        LocalDateTime createdAt
) {
}
