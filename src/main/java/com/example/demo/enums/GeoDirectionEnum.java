package com.example.demo.enums;

public enum GeoDirectionEnum implements BaseEnum{

    NORTH(0, "North"),
    EAST(1, "East"),
    SOUTH(2, "South"),
    WEST(3, "West");

    private final int value;

    private final String name;

    GeoDirectionEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static GeoDirectionEnum getInstance(int value) {
        for (GeoDirectionEnum e : GeoDirectionEnum.values()) {
            if (e.getValue() == value) return e;
        }
        return null;
    }
}
