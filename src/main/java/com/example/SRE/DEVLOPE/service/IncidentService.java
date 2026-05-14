package com.example.SRE.DEVLOPE.service;

import com.example.SRE.DEVLOPE.dto.IncidentRequest;
import com.example.SRE.DEVLOPE.model.Incident;
import com.example.SRE.DEVLOPE.repository.IncidentRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncidentService {

    private final IncidentRepository repository;

    public IncidentService(
            IncidentRepository repository
    ) {
        this.repository = repository;
    }

    public Incident create(
            IncidentRequest request
    ) {

        Incident incident = new Incident();

        incident.setType(request.getType());
        incident.setSeverity(request.getSeverity());
        incident.setStatus(request.getStatus());

        incident.setResolved(false);

        incident.setTimestamp(
                LocalDateTime.now()
        );

        return repository.save(incident);
    }

    public List<Incident> getAll() {

        return repository.findAll();
    }
}