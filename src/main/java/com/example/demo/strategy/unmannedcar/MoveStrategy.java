package com.example.demo.strategy.unmannedcar;

import com.example.demo.exception.OutOfBorderException;
import com.example.demo.model.UnmannedCar;

public interface MoveStrategy {

    void move(int step, UnmannedCar unmannedCar) throws OutOfBorderException;
}
