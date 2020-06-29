package com.example.demo.strategy.unmannedcar;

import com.example.demo.enums.GeoDirectionEnum;
import com.example.demo.exception.OutOfBorderException;
import com.example.demo.model.UnmannedCar;

/**
 * @author: dw_chenjunjian
 * @date 2020/6/29
 * @desc
 **/
public abstract class BorderMoveStrategy implements MoveStrategy{

    protected int BORDER_X_MIN = 0;
    protected int BORDER_Y_MIN = 0;
    protected int BORDER_X_MAX = 0;
    protected int BORDER_Y_MAX = 0;

    public BorderMoveStrategy(int BORDER_X_MIN, int BORDER_Y_MIN, int BORDER_X_MAX, int BORDER_Y_MAX) {
        this.BORDER_X_MIN = BORDER_X_MIN;
        this.BORDER_Y_MIN = BORDER_Y_MIN;
        this.BORDER_X_MAX = BORDER_X_MAX;
        this.BORDER_Y_MAX = BORDER_Y_MAX;
    }

    @Override
    public void move(int step, UnmannedCar unmannedCar) throws OutOfBorderException {
        if (unmannedCar.getCurrDirection().getValue() == GeoDirectionEnum.NORTH.getValue()) {
            int tempY = unmannedCar.getY() + step;
            if (tempY > this.getBORDER_Y_MAX()) {
                throw new OutOfBorderException("Beyond the Y boundary");
            }
            unmannedCar.setY(tempY);
        }else if (unmannedCar.getCurrDirection().getValue() == GeoDirectionEnum.SOUTH.getValue()) {
            int tempY = unmannedCar.getY() - step;
            if (tempY < this.getBORDER_Y_MIN()) {
                throw new OutOfBorderException("Beyond the Y boundary");
            }
            unmannedCar.setY(tempY);
        } else if (unmannedCar.getCurrDirection().getValue() == GeoDirectionEnum.EAST.getValue()) {
            int tempX = unmannedCar.getX() + step;
            if (tempX < this.getBORDER_X_MIN() || tempX > this.getBORDER_X_MAX()) {
                throw new OutOfBorderException("Beyond the X boundary");
            }
            unmannedCar.setX(tempX);
        } else if (unmannedCar.getCurrDirection().getValue() == GeoDirectionEnum.WEST.getValue()) {
            int tempX = unmannedCar.getX() - step;
            if (tempX < this.getBORDER_X_MIN()) {
                throw new OutOfBorderException("Beyond the X boundary");
            }
            unmannedCar.setX(tempX);
        }
        System.out.println(this.toString());
    }

    public int getBORDER_X_MIN() {
        return BORDER_X_MIN;
    }

    public void setBORDER_X_MIN(int BORDER_X_MIN) {
        this.BORDER_X_MIN = BORDER_X_MIN;
    }

    public int getBORDER_Y_MIN() {
        return BORDER_Y_MIN;
    }

    public void setBORDER_Y_MIN(int BORDER_Y_MIN) {
        this.BORDER_Y_MIN = BORDER_Y_MIN;
    }

    public int getBORDER_X_MAX() {
        return BORDER_X_MAX;
    }

    public void setBORDER_X_MAX(int BORDER_X_MAX) {
        this.BORDER_X_MAX = BORDER_X_MAX;
    }

    public int getBORDER_Y_MAX() {
        return BORDER_Y_MAX;
    }

    public void setBORDER_Y_MAX(int BORDER_Y_MAX) {
        this.BORDER_Y_MAX = BORDER_Y_MAX;
    }
}
