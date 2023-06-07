package com.aic.cabana.spring.files.excel.controller;

import com.aic.cabana.spring.files.excel.message.ResponseMessage;
import com.aic.cabana.spring.files.excel.model.*;
import com.aic.cabana.spring.files.excel.service.ExcelService;
import com.aic.cabana.spring.files.excel.service.GoogleMapsAPIService;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import jakarta.ws.rs.Consumes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/api/googlemaps")
public class GoogleMapsAPIController {

    @Autowired
    GoogleMapsAPIService googleMapsAPIService;

    @Autowired
    ExcelService excelService;

    private static final String KEY = "AIzaSyAfN9kx-j7QW3atFmPhgjnoOWREPGd8TOM";

    @GetMapping("/addAddressCoordinates")
    public ResponseEntity<String> addAddressCoordinates(){
        try{
            List<Cabana> addressList = excelService.getAllAddresses();
            if(addressList.size() > 0){
                googleMapsAPIService.updateCoordinates(addressList);
            }
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/fetchCoordinates")
    @Consumes("application/json")
    public ResponseEntity<Coordinates> fetchCoordinates(@RequestBody String address) throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(KEY)
                .build();
        Coordinates coordinate = googleMapsAPIService.fetchCoordinatesFromAddress(address, context);
        return new ResponseEntity<>(coordinate, HttpStatus.OK);
    }

    @PostMapping("/fetchAddress")
    @Consumes("application/json")
    public ResponseEntity<String> fetchAddress(@RequestBody Coordinates coordinates) throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(KEY)
                .build();
        String address = googleMapsAPIService.fetchAddressFromCoordinates(coordinates, context);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    //To be fixed later
    @PostMapping("/fetchDirections")
    @Consumes("application/json")
    public ResponseEntity<ResponseMessage> fetchDirections(@RequestBody DirectionRequest directionRequest) throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(KEY)
                .build();
        googleMapsAPIService.getDirections(directionRequest, context);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/fetchAvailableRentals")
    @Consumes("application/json")
    public ResponseEntity<SearchResponse> fetchAvailableRentals(@RequestBody RentalRequest rentalRequest) throws IOException, InterruptedException, ApiException {
        try{
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(KEY)
                    .build();
            SearchResponse availableRentalList = googleMapsAPIService.fetchRentalsWithinRadius(rentalRequest, context);
            return new ResponseEntity<>(availableRentalList, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
