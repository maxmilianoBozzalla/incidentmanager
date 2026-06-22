package com.maxi.incidentmanager.analysis.constants;

public final class AnalysisReasons {
    public static final String FALSE_POSITIVE_REASON = "Incident appears to have negligible operational impact and may represent a false positive.";
    public static final String RECURRING_INCIDENT_REASON = "Found recurring incidents in the last 30 days.";
    public static final String NEW_INCIDENT_REASON = "No similar incidents were found for this service in the last 30 days.";
    public static final String HIGH_IMPACT_INCIDENT_REASON = "Incident has significant business impact based on its severity and operational metrics.";
}
