package com.example.demo.strategy.unmannedcar;

import com.example.demo.enums.GeoDirectionEnum;
import com.example.demo.exception.OutOfBorderException;
import com.example.demo.model.UnmannedCar;

import java.util.Random;

/**
 * 顺时针自动驾驶模式
 */
public class RangeMoveStrategy implements MoveStrategy{

    protected int BORDER_X_MIN = 0;
    protected int BORDER_Y_MIN = 0;
    protected int BORDER_X_MAX = 0;
    protected int BORDER_Y_MAX = 0;

    public RangeMoveStrategy(int BORDER_X_MIN, int BORDER_Y_MIN, int BORDER_X_MAX, int BORDER_Y_MAX) {
        this.BORDER_X_MIN = BORDER_X_MIN;
        this.BORDER_Y_MIN = BORDER_Y_MIN;
        this.BORDER_X_MAX = BORDER_X_MAX;
        this.BORDER_Y_MAX = BORDER_Y_MAX;
    }

    @Override
    public void move(int step, UnmannedCar unmannedCar) throws OutOfBorderException {
        if (step == 0) return;
        System.out.println(String.format("{%s} move step {%d}", unmannedCar.toString(), step));
        if (unmannedCar.getCurrDirection().getValue() == GeoDirectionEnum.NORTH.getValue()) {
            int tempY = unmannedCar.getY() + step;
            if (tempY > this.getBORDER_Y_MAX()) {
                //throw new OutOfBorderException("Beyond the Y boundary");
                this.rangeRotation(unmannedCar);
                int remainingStep = step - (this.getBORDER_Y_MAX() - unmannedCar.getY());
                unmannedCar.setY(this.getBORDER_Y_MAX());
                unmannedCar.autoMove(remainingStep, this);
            } else {
                unmannedCar.setY(tempY);
            }
        } else if (unmannedCar.getCurrDirection().getValue() == GeoDirectionEnum.SOUTH.getValue()) {
            int tempY = unmannedCar.getY() - step;
            if (tempY < this.getBORDER_Y_MIN()) {
                //throw new OutOfBorderException("Beyond the Y boundary");
                this.rangeRotation(unmannedCar);
                int remainingStep = step - unmannedCar.getY();
                unmannedCar.setY(this.getBORDER_Y_MIN());
                unmannedCar.autoMove(remainingStep, this);
            } else {
                unmannedCar.setY(tempY);
            }
        } else if (unmannedCar.getCurrDirection().getValue() == GeoDirectionEnum.EAST.getValue()) {
            int tempX = unmannedCar.getX() + step;
            if (tempX > this.getBORDER_X_MAX()) {
                this.rangeRotation(unmannedCar);
                int remainingStep = step - (this.getBORDER_X_MAX() - unmannedCar.getX());
                unmannedCar.setX(this.getBORDER_X_MAX());
                unmannedCar.autoMove(remainingStep, this);
            } else {
                unmannedCar.setX(tempX);
            }
        } else if (unmannedCar.getCurrDirection().getValue() == GeoDirectionEnum.WEST.getValue()) {
            int tempX = unmannedCar.getX() - step;
            if (tempX < this.getBORDER_X_MIN()) {
                this.rangeRotation(unmannedCar);
                int remainingStep = step - unmannedCar.getX();
                unmannedCar.setX(this.getBORDER_X_MIN());
                unmannedCar.autoMove(remainingStep, this);
            } else {
                unmannedCar.setX(tempX);
            }
        }
    }

    //随机转向
    private void rangeRotation(UnmannedCar unmannedCar) {
        int range = new Random().nextInt(4);
        GeoDirectionEnum newDirection = GeoDirectionEnum.getInstance((unmannedCar.getCurrDirection().getValue() + range)%4);
        if (newDirection.getValue() == unmannedCar.getCurrDirection().getValue()) {
            rangeRotation(unmannedCar);
        }
        System.out.println(String.format("Beyond the boundary, turn automatically:{%s} -> {%s}",
                unmannedCar.getCurrDirection().getName(), newDirection.getName()));
        unmannedCar.turnDirection(newDirection);
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
