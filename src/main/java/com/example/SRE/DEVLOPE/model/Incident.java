package com.example.SRE.DEVLOPE.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String severity;

    private String status;

    private boolean resolved;

    private LocalDateTime timestamp;

    public Incident(){

    }

    public Incident(Long id, String type, String severity, String status, boolean resolved, LocalDateTime timestamp) {
        this.id = id;
        this.type = type;
        this.severity = severity;
        this.status = status;
        this.resolved = resolved;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}