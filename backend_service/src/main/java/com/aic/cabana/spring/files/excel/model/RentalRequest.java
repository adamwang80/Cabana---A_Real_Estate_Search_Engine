package com.aic.cabana.spring.files.excel.model;

public class RentalRequest {

    String destination;
    String commuteType;
    String bedroomType;
    long searchRadius;
    Double latitude;
    Double longitude;

    public RentalRequest() {
    }

    public RentalRequest(String destination, String commuteType, String bedroomType, long searchRadius, Double latitude, Double longitude) {
        this.destination = destination;
        this.commuteType = commuteType;
        this.bedroomType = bedroomType;
        this.searchRadius = searchRadius;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCommuteType() {
        return commuteType;
    }

    public void setCommuteType(String commuteType) {
        this.commuteType = commuteType;
    }

    public String getBedroomType() {
        return bedroomType;
    }

    public void setBedroomType(String bedroomType) {
        this.bedroomType = bedroomType;
    }

    public long getSearchRadius() {
        return searchRadius;
    }

    public void setSearchRadius(long searchRadius) {
        this.searchRadius = searchRadius;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "RentalRequest{" +
                "destination='" + destination + '\'' +
                ", commuteType='" + commuteType + '\'' +
                ", bedroomType='" + bedroomType + '\'' +
                ", searchRadius=" + searchRadius +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
