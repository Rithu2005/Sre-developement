package com.example.SRE.DEVLOPE.controller;

import com.example.SRE.DEVLOPE.dto.IncidentRequest;
import com.example.SRE.DEVLOPE.model.Incident;
import com.example.SRE.DEVLOPE.service.IncidentService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentService service;

    public IncidentController(
            IncidentService service
    ) {
        this.service = service;
    }

    @PostMapping
    public Incident create(
            @RequestBody IncidentRequest request
    ) {
        return service.create(request);
    }

    @GetMapping
    public List<Incident> getAll() {
        return service.getAll();
    }
}