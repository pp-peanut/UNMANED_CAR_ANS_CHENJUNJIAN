package com.example.demo.test;

import com.example.demo.enums.GeoDirectionEnum;
import com.example.demo.model.Car;
import com.example.demo.model.UnmannedCar;
import com.example.demo.strategy.unmannedcar.ClockwiseMoveStrategy;
import com.example.demo.strategy.unmannedcar.DefaultMoveStrategy;
import com.example.demo.strategy.unmannedcar.RangeMoveStrategy;
import org.junit.Test;

public class CarMoveTest {

    /**
     * 正常模式下，超越边界发生异常
     * @throws Exception
     */
    @Test
    public void testDefaultMove() throws Exception{
        int initX = 1, initY = 1;
        int xBorderMin = 0, yBorderMin = 0;
        int xBorderMax = 4, yBorderMax = 4;
        GeoDirectionEnum geoDirectionEnum = GeoDirectionEnum.NORTH;
        DefaultMoveStrategy defaultMoveStrategy = new DefaultMoveStrategy(xBorderMin, yBorderMin, xBorderMax, yBorderMax);
        Car car = new UnmannedCar(initX, initY, geoDirectionEnum, defaultMoveStrategy);

        for (int i = 0; i < 6; i++) {
            try {
                car.move(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 顺时针自动行驶策略，通过直接传入策略来进行调用
     * @throws Exception
     */
    @Test
    public void testClockwiseMove() throws Exception{
        int initX = 1, initY = 1;
        int xBorderMin = 0, yBorderMin = 0;
        int xBorderMax = 4, yBorderMax = 4;
        GeoDirectionEnum geoDirectionEnum = GeoDirectionEnum.NORTH;
        UnmannedCar car = new UnmannedCar(initX, initY, geoDirectionEnum);
        ClockwiseMoveStrategy testClockwiseMoveStrategy = new ClockwiseMoveStrategy(xBorderMin, yBorderMin, xBorderMax, yBorderMax);
        for (int i = 0; i < 100; i++) {
            try {
                car.autoMove(1, testClockwiseMoveStrategy);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 顺时针自动行驶策略，也可在实例里生辰一个策略成员并对其赋值，就可以使用这个策略来进行移动
     * 初始化汽车位置时校验边界，会抛出异常
     * @throws Exception
     */
    @Test
    public void testClockwiseMove2() throws Exception{
        int initX = 0, initY = 0;
        int xBorderMin = 1, yBorderMin = 0;
        int xBorderMax = 4, yBorderMax = 4;
        GeoDirectionEnum geoDirectionEnum = GeoDirectionEnum.SOUTH;
        ClockwiseMoveStrategy testClockwiseMoveStrategy = new ClockwiseMoveStrategy(xBorderMin, yBorderMin, xBorderMax, yBorderMax);
        Car car = new UnmannedCar(initX, initY, geoDirectionEnum, testClockwiseMoveStrategy);

        for (int i = 0; i < 100; i++) {
            try {
                car.move(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testRangeMove() throws Exception{
        int initX = 1, initY = 1;
        int xBorderMin = 1, yBorderMin = 1;
        int xBorderMax = 1, yBorderMax = 12;
        GeoDirectionEnum geoDirectionEnum = GeoDirectionEnum.NORTH;
        UnmannedCar car = new UnmannedCar(initX, initY, geoDirectionEnum);
        RangeMoveStrategy testRangeMoveStrategy = new RangeMoveStrategy(xBorderMin, yBorderMin, xBorderMax, yBorderMax);
        for (int i = 0; i < 100; i++) {
            try {
                car.autoMove(1, testRangeMoveStrategy);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
