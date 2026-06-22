package com.maxi.incidentmanager.analysis.service;

import com.maxi.incidentmanager.analysis.constants.SeverityScoreThresholds;
import com.maxi.incidentmanager.incident.entity.Incident;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class SeverityScoreCalculatorImp implements SeverityScoreCalculator {

    @Override
    public int calculateScore(Incident incident) {

        BigDecimal errorRate = incident.getErrorRate();
        long requestCount = incident.getRequestCount();
        long avgResponseTime = incident.getAvgResponseTime();
        int impactedUsers = incident.getImpactedUsers();
        log.info(
                """
                Incident Metrics:
                  errorRate={}
                  requestCount={}
                  avgResponseTime={}
                  impactedUsers={}
                """,
                errorRate,
                requestCount,
                avgResponseTime,
                impactedUsers
        );

        int scoreErrorRate = getScoreErrorRate(errorRate);
        int scoreRequestCount = getScoreRequestCount(requestCount);
        int scoreResponseTime = getScoreResponseTime(avgResponseTime);
        int scoreImpactedUsers = getScoreImpactedUser(impactedUsers);
        log.info(
                """
                Severity Scores:
                  errorRate={}
                  requestCount={}
                  responseTime={}
                  impactedUsers={}
                """,
                scoreErrorRate,
                scoreRequestCount,
                scoreResponseTime,
                scoreImpactedUsers
        );

        return scoreErrorRate + scoreRequestCount + scoreResponseTime + scoreImpactedUsers;
    }

    private int getScoreErrorRate(BigDecimal errorRate) {
        int scoreErrorRate = 0;

        if (errorRate.compareTo(BigDecimal.valueOf(1)) <= 0) {
            scoreErrorRate = SeverityScoreThresholds.ERROR_RATE_LOW_THRESHOLD;

        } else if (errorRate.compareTo(BigDecimal.valueOf(5)) <= 0) {
            scoreErrorRate = SeverityScoreThresholds.ERROR_RATE_MEDIUM_THRESHOLD;

        } else if (errorRate.compareTo(BigDecimal.valueOf(15)) <= 0) {
            scoreErrorRate = SeverityScoreThresholds.ERROR_RATE_HIGH_THRESHOLD;

        } else {
            scoreErrorRate = SeverityScoreThresholds.ERROR_RATE_CRITICAL_THRESHOLD;
        }

        return scoreErrorRate;
    }

    private int getScoreRequestCount(long requestsCount) {

        int scoreRequestCount = 0;

        if (requestsCount <= 5) {
            scoreRequestCount = SeverityScoreThresholds.REQUEST_COUNT_LOW_THRESHOLD;

        } else if (requestsCount <= 30) {
            scoreRequestCount = SeverityScoreThresholds.REQUEST_COUNT_MEDIUM_THRESHOLD;

        } else if (requestsCount <= 150) {
            scoreRequestCount = SeverityScoreThresholds.REQUEST_COUNT_HIGH_THRESHOLD;

        } else {
            scoreRequestCount = SeverityScoreThresholds.REQUEST_COUNT_CRITICAL_THRESHOLD;
        }
        return scoreRequestCount;
    }

    private int getScoreResponseTime(long avgResponseTime) {
        int scoreResponseTime = 0;

        if (avgResponseTime <= 500) {
            scoreResponseTime = SeverityScoreThresholds.RESPONSE_TIME_LOW_THRESHOLD;

        } else if (avgResponseTime <= 1000) {
            scoreResponseTime = SeverityScoreThresholds.RESPONSE_TIME_MEDIUM_THRESHOLD;

        } else if (avgResponseTime <= 3000) {
            scoreResponseTime = SeverityScoreThresholds.RESPONSE_TIME_HIGH_THRESHOLD;

        } else {
            scoreResponseTime = SeverityScoreThresholds.RESPONSE_TIME_CRITICAL_THRESHOLD;
        }
        return scoreResponseTime;
    }

    private int getScoreImpactedUser(int impactedUsers) {
        int scoreImpactedUsers = 0;

        if (impactedUsers <= 50) {
            scoreImpactedUsers = SeverityScoreThresholds.IMPACTED_USERS_LOW_THRESHOLD;

        } else if (impactedUsers <= 200) {
            scoreImpactedUsers = SeverityScoreThresholds.IMPACTED_USERS_MEDIUM_THRESHOLD;

        } else if (impactedUsers <= 1000) {
            scoreImpactedUsers = SeverityScoreThresholds.IMPACTED_USERS_HIGH_THRESHOLD;

        } else {
            scoreImpactedUsers = SeverityScoreThresholds.IMPACTED_USERS_CRITICAL_THRESHOLD;
        }

        return scoreImpactedUsers;

    }

}
