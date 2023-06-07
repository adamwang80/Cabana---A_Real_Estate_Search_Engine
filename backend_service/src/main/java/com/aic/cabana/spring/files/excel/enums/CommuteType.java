package com.aic.cabana.spring.files.excel.enums;

import com.google.maps.model.TravelMode;

import java.util.HashMap;
import java.util.Map;

public enum CommuteType {

    DRIVING (1, "DRIVING", TravelMode.DRIVING),
    WALKING (2, "WALKING", TravelMode.WALKING),
    CYCLING (3, "BICYCLING", TravelMode.BICYCLING),
    TRANSIT(4, "TRANSIT", TravelMode.TRANSIT);

    public static Map<String, CommuteType> commuteTypeMap = new HashMap<>();

    static{
        for(CommuteType commuteType : CommuteType.values()){
            commuteTypeMap.put(commuteType.description, commuteType);
        }
    }

    private final int id;
    private final String description;
    private final TravelMode travelMode;

    CommuteType(int id, String description, TravelMode travelMode) {
        this.id = id;
        this.description = description;
        this.travelMode = travelMode;
    }

    public static Map<String, CommuteType> getCommuteTypeMap() {
        return commuteTypeMap;
    }

    public static void setCommuteTypeMap(Map<String, CommuteType> commuteTypeMap) {
        CommuteType.commuteTypeMap = commuteTypeMap;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public TravelMode getTravelMode() {
        return travelMode;
    }
}
