package com.example.SRE.DEVLOPE.dto;

public class IncidentRequest {

    private String type;
    private String severity;
    private String status;

    public IncidentRequest(){

    }

    public IncidentRequest(String type, String severity, String status) {
        this.type = type;
        this.severity = severity;
        this.status = status;
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
}