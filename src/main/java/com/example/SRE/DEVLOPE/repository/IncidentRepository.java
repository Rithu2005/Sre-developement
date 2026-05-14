package com.example.SRE.DEVLOPE.repository;

import com.example.SRE.DEVLOPE.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository
        extends JpaRepository<Incident, Long> {
}