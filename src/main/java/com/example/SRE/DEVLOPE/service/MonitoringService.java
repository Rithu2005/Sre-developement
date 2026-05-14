package com.example.SRE.DEVLOPE.service;

import org.springframework.stereotype.Service;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MonitoringService {

    private final CloudWatchClient cloudWatchClient;

    public MonitoringService() {

        cloudWatchClient = CloudWatchClient.builder()
                .region(Region.AP_SOUTH_1)
                .build();
    }

    public Map<String, Object> getMetrics(String instanceId) {

        Instant endTime = Instant.now();
        Instant startTime = endTime.minusSeconds(3600);

        Dimension dimension = Dimension.builder()
                .name("InstanceId")
                .value(instanceId)
                .build();

        GetMetricStatisticsRequest request =
                GetMetricStatisticsRequest.builder()
                        .namespace("AWS/EC2")
                        .metricName("CPUUtilization")
                        .dimensions(dimension)
                        .startTime(startTime)
                        .endTime(endTime)
                        .period(300)
                        .statistics(Statistic.AVERAGE)
                        .build();

        GetMetricStatisticsResponse response =
                cloudWatchClient.getMetricStatistics(request);

        List<Datapoint> datapoints = response.datapoints();

        Map<String, Object> result = new HashMap<>();

        if (datapoints == null || datapoints.isEmpty()) {

            result.put("cpuUsage", 0);
            result.put("message", "No metrics available");

            return result;
        }

        double cpuUsage = datapoints.get(0).average();

        result.put("cpuUsage", cpuUsage);

        return result;
    }
}