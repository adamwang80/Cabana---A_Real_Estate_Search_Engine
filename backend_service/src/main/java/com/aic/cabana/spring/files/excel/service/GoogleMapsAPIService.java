package com.aic.cabana.spring.files.excel.service;

import com.aic.cabana.spring.files.excel.constants.GoogleMapsConstants;
import com.aic.cabana.spring.files.excel.enums.CommuteType;
import com.aic.cabana.spring.files.excel.enums.RoomType;
import com.aic.cabana.spring.files.excel.model.*;
import com.aic.cabana.spring.files.excel.repository.CabanaRepository;
import com.aic.cabana.spring.files.excel.util.StopWatch;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class GoogleMapsAPIService {

    @Autowired
    CabanaRepository cabanaRepository;

    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static Logger LOGGER = Logger.getLogger(GoogleMapsAPIService.class.getName());

    public void updateCoordinates(List<Cabana> addressList) throws Exception{
        try{
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(GoogleMapsConstants.KEY)
                    .build();
            for(Cabana address : addressList){
                Coordinates coordinates = fetchCoordinatesFromAddress(address.getAddress(), context);
                address.setLatitude(coordinates.getLatitude());
                address.setLongitude(coordinates.getLongitude());
                cabanaRepository.save(address);
            }
            context.shutdown();
        }catch(Exception e){
            throw new Exception("Update failed", e);
        }
    }

    public List<String> formatAddress(List<String> addresslist) throws UnsupportedEncodingException {
        List<String> formattedAddressList = new ArrayList<>();
        for(String address : addresslist){
            String encodedAddress = URLEncoder.encode(address, "UTF-8");
            formattedAddressList.add(encodedAddress);
        }
        return formattedAddressList;
    }

    public Coordinates fetchCoordinatesFromAddress(String address, GeoApiContext context) throws IOException, InterruptedException, ApiException {
        GeocodingResult[] results =  GeocodingApi.geocode(context,
                address).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Coordinates newCo = new Coordinates(Double.parseDouble(gson.toJson(results[0].geometry.location.lat)), Double.parseDouble(gson.toJson(results[0].geometry.location.lng)));
        return newCo;
    }

    public String fetchAddressFromCoordinates(Coordinates coordinates, GeoApiContext context) throws IOException, InterruptedException, ApiException {
        GeocodingResult[] results =  GeocodingApi.reverseGeocode(context,
                new LatLng(coordinates.getLatitude(), coordinates.getLongitude())).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String address = gson.toJson(results[0].formattedAddress);
        return address;
    }

    //To be fixed later
    public void getDirections(DirectionRequest directionRequest, GeoApiContext context) throws IOException, InterruptedException, ApiException {
        DirectionsResult results = DirectionsApi.getDirections(context,
                directionRequest.getSource(), directionRequest.getDestination()).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String geocodedWaypoints = gson.toJson(results);
        //String routes = gson.toJson(results.routes);
        System.out.println("geocodedWaypoints :: " + geocodedWaypoints);
        //System.out.println("routes :: " + routes);
    }

    public SearchResponse fetchRentalsWithinRadius(RentalRequest rentalRequest, GeoApiContext context) throws IOException, InterruptedException, ApiException {
        StopWatch stopWatch = new StopWatch();

        SearchResponse searchResponse = new SearchResponse();
        List<RentalResponse> availableRentalList = new ArrayList<>();
        //fetch dest lat,lng
        Coordinates coordinates = fetchCoordinatesFromAddress(rentalRequest.getDestination(), context);
        rentalRequest = buildRentalRequest(rentalRequest, coordinates);
        availableRentalList = buildRentalResponse(rentalRequest, availableRentalList, context, coordinates);
        searchResponse.setRentalRequest(rentalRequest);
        searchResponse.setRentalResponse(availableRentalList);

        LOGGER.info("Execution took in seconds: " + (stopWatch.getElapsedTime()));
        return searchResponse;
    }

    public RentalRequest buildRentalRequest(RentalRequest rentalRequest, Coordinates coordinates){
        rentalRequest.setLatitude(coordinates.getLatitude());
        rentalRequest.setLongitude(coordinates.getLongitude());
        return rentalRequest;
    }

    public List<RentalResponse> buildRentalResponse(RentalRequest rentalRequest, List<RentalResponse> availableRentalList, GeoApiContext context, Coordinates coordinates) throws IOException, InterruptedException, ApiException {
        Double startLat = coordinates.getLatitude() + rentalRequest.getSearchRadius()*-0.1;
        Double startLng = coordinates.getLongitude() + rentalRequest.getSearchRadius()*-0.1;

        //compute radius using lat,lng
        Double endLat = coordinates.getLatitude() + rentalRequest.getSearchRadius()*0.1;
        Double endLng = coordinates.getLongitude() + rentalRequest.getSearchRadius()*0.1;

        //fetch all the address ids from db, falling within the above lat,lng range
        List<Cabana> cabanaList = cabanaRepository.retrieveByCoordinateRange(startLat, startLng, endLat, endLng);

        //List<Cabana> cabanaList = cabanaRepository.findAll();
        //now use the dest lat,lng and the new obtained address ids to call direction matrix
        for(Cabana cabana : cabanaList){
            Long price = fetchUnitPrice(rentalRequest.getBedroomType(), cabana);
            if(price != 0) {
                String origins[] = {rentalRequest.getDestination()};
                String destinations[] = {cabana.getAddress()};


                DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
                DistanceMatrix matrix = req.origins(destinations)
                        .destinations(origins)
                        .mode(CommuteType.commuteTypeMap.get(rentalRequest.getCommuteType()).getTravelMode())
                        //.units(Unit.IMPERIAL)
                        .await();

                //CommuteType.commuteTypeMap.get(rentalRequest.getCommuteType()).getTravelMode()
                //DistanceMatrix matrix = DistanceMatrixApi.getDistanceMatrix(context, origins, destinations).await();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String distance = gson.toJson(matrix.rows[0].elements[0].distance.humanReadable);

                //if location dist is within search radius
                float distInKM = getDistInLong(distance);
                double miles = distInKM / 1.6;
                if(miles <= (double)rentalRequest.getSearchRadius()){
                    String duration = gson.toJson(matrix.rows[0].elements[0].duration.humanReadable);
                    RentalResponse rental = new RentalResponse();
                    rental.setApartmentName(cabana.getApartmentName());
                    rental.setLatitude(cabana.getLatitude());
                    rental.setLongitude(cabana.getLongitude());
                    rental.setAddress(trim(fetchAddressFromCoordinates(new Coordinates(cabana.getLatitude(), cabana.getLongitude()), context))); //not using db address for accuracy
                    rental.setUnit(rentalRequest.getBedroomType());
                    rental.setDistance(computeDistInMiles(distance));
                    rental.setDuration(trim(duration));
                    rental.setImageLink(cabana.getImages());
                    rental.setPrice(price);
                    availableRentalList.add(rental);
                }
            }
        }
        return availableRentalList;
    }

    public Long fetchUnitPrice(String bedroomType, Cabana cabana){
        Long price = 0L;
        RoomType roomType = RoomType.roomTypeMap.get(bedroomType);
        switch(roomType){
            case ONE_BED:
                price = cabana.getRent1bd();
                break;
            case TWO_BED:
                price = cabana.getRent2bds();
                break;
            case THREE_BED:
                price = cabana.getRent3bds();
                break;
            case FOUR_BED:
                price = cabana.getRent4bds();
                break;
            case FIVE_BED:
                price = cabana.getRent5bds();
                break;
            case STUDIO:
                price = cabana.getRentStudio();
                break;
        }
        return price;
    }

    public String trim(String input){
        String result = "";
        int size = input.length();
        result = input.substring(1,size-1);
        return result;
    }

    public Float getDistInLong(String input){
        input = trim(input);
        String result[] = input.split("\\s");
        String dist = result[0].trim();
        dist = dist.replaceAll(",", "");
        return Float.valueOf(dist);
    }

    public String computeDistInMiles(String distance){
        float distInKM = getDistInLong(distance);
        double miles = distInKM / 1.6;
        String distInMiles = df.format(miles) + " miles";
        return distInMiles;
    }


}