package com.example.demo.model;

import com.example.demo.enums.GeoDirectionEnum;

public abstract class Car {

    protected int x = 0;

    protected int y = 0;

    protected GeoDirectionEnum currDirection = GeoDirectionEnum.NORTH;

    public abstract void move(int step) throws Exception;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public GeoDirectionEnum getCurrDirection() {
        return currDirection;
    }

    public void setCurrDirection(GeoDirectionEnum currDirection) {
        this.currDirection = currDirection;
    }
}
