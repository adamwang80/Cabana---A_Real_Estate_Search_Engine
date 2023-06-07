package com.aic.cabana.spring.files.excel.model;

public class DirectionRequest {

    String source;
    String destination;

    public DirectionRequest() {
    }

    public DirectionRequest(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "DirectionRequest{" +
                "source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
