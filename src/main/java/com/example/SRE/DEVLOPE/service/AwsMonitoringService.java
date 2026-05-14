package com.example.SRE.DEVLOPE.service;


import org.springframework.stereotype.Service;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.Datapoint;
import software.amazon.awssdk.services.cloudwatch.model.Dimension;
import software.amazon.awssdk.services.cloudwatch.model.GetMetricStatisticsRequest;
import software.amazon.awssdk.services.cloudwatch.model.GetMetricStatisticsResponse;
import software.amazon.awssdk.services.cloudwatch.model.Statistic;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AwsMonitoringService {

    private final CloudWatchClient cloudWatchClient;

    public AwsMonitoringService() {

        this.cloudWatchClient = CloudWatchClient.builder()
                .region(Region.AP_SOUTH_1)
                .build();
    }

    public Map<String, Double> getEC2Metrics(String instanceId) {

        Map<String, Double> metrics = new HashMap<>();

        double cpu = getMetric(
                instanceId,
                "CPUUtilization",
                "AWS/EC2"
        );

        metrics.put("cpuUsage", cpu);

        return metrics;
    }

    private double getMetric(
            String instanceId,
            String metricName,
            String namespace
    ) {

        Instant endTime = Instant.now();
        Instant startTime = endTime.minusSeconds(3600);

        GetMetricStatisticsRequest request =
                GetMetricStatisticsRequest.builder()
                        .namespace(namespace)
                        .metricName(metricName)
                        .dimensions(
                                Dimension.builder()
                                        .name("InstanceId")
                                        .value(instanceId)
                                        .build()
                        )
                        .startTime(startTime)
                        .endTime(endTime)
                        .period(300)
                        .statistics(Statistic.AVERAGE)
                        .build();

        GetMetricStatisticsResponse response =
                cloudWatchClient.getMetricStatistics(request);

        List<Datapoint> datapoints = response.datapoints();

        if (datapoints.isEmpty()) {
            return 0.0;
        }

        return datapoints.get(0).average();
    }
}