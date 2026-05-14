package com.example.SRE.DEVLOPE.controller;

import com.example.SRE.DEVLOPE.service.MonitoringService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/metrics")
@CrossOrigin("*")
public class MonitoringController {

    private final MonitoringService monitoringService;

    public MonitoringController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @GetMapping("/system")
    public ResponseEntity<?> getSystemMetrics(
            @RequestParam String instanceId) {

        try {

            Map<String, Object> data =
                    monitoringService.getMetrics(instanceId);

            return ResponseEntity.ok(data);

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }
}