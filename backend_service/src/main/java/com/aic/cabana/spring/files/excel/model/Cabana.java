package com.aic.cabana.spring.files.excel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cabana")
public class Cabana {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "scrapperOrder")
    private String scrapperOrder;

    @Column(name = "link")
    private String link;

    @Column(name = "apartmentName")
    private String apartmentName;

    @Column(name = "address")
    private String address;

    @Column(name = "rentStudio")
    private Long rentStudio;

    @Column(name = "rent_1bd")
    private Long rent1bd;

    @Column(name = "rent_2bds")
    private Long rent2bds;

    @Column(name = "rent_3bds")
    private Long rent3bds;

    @Column(name = "rent_4bds")
    private Long rent4bds;

    @Column(name = "rent_5bds")
    private Long rent5bds;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "images")
    private String images;

    public Cabana() {

    }

    public Cabana(String scrapperOrder, String link, String apartmentName, String address, Long rentStudio, Long rent_1bd,
                  Long rent_2bds, Long rent_3bds, Long rent_4bds, Long rent_5bds, Double latitude, Double longitude, String images) {
        this.scrapperOrder = scrapperOrder;
        this.apartmentName = apartmentName;
        this.link = link;
        this.address = address;
        this.rentStudio = rentStudio;
        this.rent1bd = rent_1bd;
        this.rent2bds = rent_2bds;
        this.rent3bds = rent_3bds;
        this.rent4bds = rent_4bds;
        this.rent5bds = rent_5bds;
        this.latitude = latitude;
        this.longitude = longitude;
        this.images = images;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getScrapperOrder() {
        return scrapperOrder;
    }

    public void setScrapperOrder(String scrapperOrder) {
        this.scrapperOrder = scrapperOrder;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getRentStudio() {
        return rentStudio;
    }

    public void setRentStudio(Long rentStudio) {
        this.rentStudio = rentStudio;
    }

    public Long getRent1bd() {
        return rent1bd;
    }

    public void setRent1bd(Long rent1bd) {
        this.rent1bd = rent1bd;
    }

    public Long getRent2bds() {
        return rent2bds;
    }

    public void setRent2bds(Long rent2bds) {
        this.rent2bds = rent2bds;
    }

    public Long getRent3bds() {
        return rent3bds;
    }

    public void setRent3bds(Long rent3bds) {
        this.rent3bds = rent3bds;
    }

    public Long getRent4bds() {
        return rent4bds;
    }

    public void setRent4bds(Long rent4bds) {
        this.rent4bds = rent4bds;
    }

    public Long getRent5bds() {
        return rent5bds;
    }

    public void setRent5bds(Long rent5bds) {
        this.rent5bds = rent5bds;
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Cabana{" +
                "id=" + id +
                ", scrapperOrder='" + scrapperOrder + '\'' +
                ", link='" + link + '\'' +
                ", apartmentName='" + apartmentName + '\'' +
                ", address='" + address + '\'' +
                ", rentStudio=" + rentStudio +
                ", rent1bd=" + rent1bd +
                ", rent2bds=" + rent2bds +
                ", rent3bds=" + rent3bds +
                ", rent4bds=" + rent4bds +
                ", rent5bds=" + rent5bds +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", images='" + images + '\'' +
                '}';
    }
}
