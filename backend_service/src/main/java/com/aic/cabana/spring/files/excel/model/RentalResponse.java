package com.aic.cabana.spring.files.excel.model;

public class RentalResponse {

    String apartmentName;
    Double latitude;
    Double longitude;
    String address;
    String unit;
    Long price;
    String distance;
    String duration;
    String imageLink;

    public RentalResponse() {
    }

    public RentalResponse(String apartmentName, Double latitude, Double longitude, String address, String unit, Long price, String distance, String duration, String imageLink) {
        this.apartmentName = apartmentName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.unit = unit;
        this.price = price;
        this.distance = distance;
        this.duration = duration;
        this.imageLink = imageLink;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "apartmentName='" + apartmentName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", address='" + address + '\'' +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", distance='" + distance + '\'' +
                ", duration='" + duration + '\'' +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
