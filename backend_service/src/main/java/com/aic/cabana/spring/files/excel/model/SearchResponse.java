package com.aic.cabana.spring.files.excel.model;

import java.util.List;

public class SearchResponse {

    RentalRequest rentalRequest;
    List<RentalResponse> rentalResponse;

    public SearchResponse() {
    }

    public SearchResponse(RentalRequest rentalRequest, List<RentalResponse> rentalResponse) {
        this.rentalRequest = rentalRequest;
        this.rentalResponse = rentalResponse;
    }

    public RentalRequest getRentalRequest() {
        return rentalRequest;
    }

    public void setRentalRequest(RentalRequest rentalRequest) {
        this.rentalRequest = rentalRequest;
    }

    public List<RentalResponse> getRentalResponse() {
        return rentalResponse;
    }

    public void setRentalResponse(List<RentalResponse> rentalResponse) {
        this.rentalResponse = rentalResponse;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "rentalRequest=" + rentalRequest +
                ", rentalResponse=" + rentalResponse +
                '}';
    }
}
