package com.example.demo.model;

import com.example.demo.enums.GeoDirectionEnum;
import com.example.demo.exception.InitUnmanedCarException;
import com.example.demo.exception.OutOfBorderException;
import com.example.demo.strategy.unmannedcar.BorderMoveStrategy;
import com.example.demo.strategy.unmannedcar.ClockwiseMoveStrategy;
import com.example.demo.strategy.unmannedcar.MoveStrategy;

public class UnmannedCar extends Car {

    MoveStrategy moveStrategy;

    public UnmannedCar() {
    }

    public UnmannedCar(int x, int y, GeoDirectionEnum geoDirectionEnum) {
        this.x = x;
        this.y = y;
        this.currDirection = geoDirectionEnum;
    }

    public UnmannedCar(int x, int y, GeoDirectionEnum geoDirectionEnum, MoveStrategy moveStrategy) throws InitUnmanedCarException {
        this.x = x;
        this.y = y;
        this.currDirection = geoDirectionEnum;
        this.moveStrategy = moveStrategy;
        if (moveStrategy instanceof BorderMoveStrategy) {
            BorderMoveStrategy temp = (BorderMoveStrategy)moveStrategy;
            if (x < temp.getBORDER_X_MIN() || x > temp.getBORDER_X_MAX() || y < temp.getBORDER_Y_MIN() || y > temp.getBORDER_Y_MAX()) {
                throw new InitUnmanedCarException("The car was initialized incorrectly");
            }
        }
    }

    @Override
    public void move(int step) throws Exception {
        //使用策略移动
        if (null != moveStrategy) {
            this.autoMove(step, moveStrategy);
//            System.out.println(this.toString());
            return;
        }
        //普通移动
        if (this.getCurrDirection().getValue() == GeoDirectionEnum.NORTH.getValue()) {
            int tempY = this.getY() + step;
            this.setY(tempY);
        }else if (this.getCurrDirection().getValue() == GeoDirectionEnum.SOUTH.getValue()) {
            int tempY = this.getY() - step;
            this.setY(tempY);
        } else if (this.getCurrDirection().getValue() == GeoDirectionEnum.EAST.getValue()) {
            int tempX = this.getX() + step;
            this.setX(tempX);
        } else if (this.getCurrDirection().getValue() == GeoDirectionEnum.WEST.getValue()) {
            int tempX = this.getX() - step;
            this.setX(tempX);
        }
        System.out.println(this.toString());
    }

    //增加策略移动
    public void autoMove(int step, MoveStrategy moveStrategy) throws OutOfBorderException {
        moveStrategy.move(step, this);
    }

    public void turnDirection(GeoDirectionEnum direction) {
        this.currDirection = direction;
    }

    @Override
    public String toString() {
        return "UnmannedCar{" +
                "currX=" + x +
                ", currY=" + y +
                ", currDirection=" + currDirection.getName() +
                '}';
    }

}
