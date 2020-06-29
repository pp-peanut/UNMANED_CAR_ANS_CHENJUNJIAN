package com.example.demo.enums;

public enum NormalDirectionEnum implements BaseEnum{

    FORWARD(0, "Forward"),
    RIGHT(1, "Right"),
    BACK(2, "Back"),
    LEFT(3, "Left");

    private final int value;

    private final String name;

    NormalDirectionEnum(int value, String name) {
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

    public static NormalDirectionEnum getInstance(int value) {
        for (NormalDirectionEnum e : NormalDirectionEnum.values()) {
            if (e.getValue() == value) return e;
        }
        return null;
    }
}
