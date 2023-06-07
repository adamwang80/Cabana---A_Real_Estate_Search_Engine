package com.aic.cabana.spring.files.excel.enums;

import java.util.HashMap;
import java.util.Map;

public enum RoomType {

    STUDIO (1, "1bed", "rent_1bd"),
    ONE_BED (2, "2bed", "rent_2bds"),
    TWO_BED (3, "3bed", "rent_3bds"),
    THREE_BED (4, "4bed", "rent_4bds"),
    FOUR_BED (5, "5bed", "rent_5bds"),
    FIVE_BED (6, "studio", "rent_studio");

    public static Map<String, RoomType> roomTypeMap = new HashMap<>();

    static{
        for(RoomType roomType : RoomType.values()){
            roomTypeMap.put(roomType.description, roomType);
        }
    }

    private final int id;
    private final String description;
    private final String dbRoomType;

    RoomType(int id, String description, String dbRoomType) {
        this.id = id;
        this.description = description;
        this.dbRoomType = dbRoomType;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
