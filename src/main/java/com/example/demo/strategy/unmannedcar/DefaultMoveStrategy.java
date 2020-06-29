package com.example.demo.strategy.unmannedcar;

import com.example.demo.exception.OutOfBorderException;
import com.example.demo.model.UnmannedCar;

public class DefaultMoveStrategy extends BorderMoveStrategy{


    public DefaultMoveStrategy(int BORDER_X_MIN, int BORDER_Y_MIN, int BORDER_X_MAX, int BORDER_Y_MAX) {
        super(BORDER_X_MIN, BORDER_Y_MIN, BORDER_X_MAX, BORDER_Y_MAX);
    }

    @Override
    public String toString() {
        return "DefaultMoveStrategy{" +
                "BORDER_X_MIN=" + BORDER_X_MIN +
                ", BORDER_Y_MIN=" + BORDER_Y_MIN +
                ", BORDER_X_MAX=" + BORDER_X_MAX +
                ", BORDER_Y_MAX=" + BORDER_Y_MAX +
                '}';
    }
}
