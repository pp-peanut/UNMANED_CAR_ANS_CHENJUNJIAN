package com.example.demo.strategy.unmannedcar;

import com.example.demo.enums.GeoDirectionEnum;
import com.example.demo.exception.OutOfBorderException;
import com.example.demo.model.UnmannedCar;

/**
 * 顺时针自动驾驶模式
 */
public class ClockwiseMoveStrategy extends BorderMoveStrategy{

    public ClockwiseMoveStrategy(int BORDER_X_MIN, int BORDER_Y_MIN, int BORDER_X_MAX, int BORDER_Y_MAX) {
        super(BORDER_X_MIN, BORDER_Y_MIN, BORDER_X_MAX, BORDER_Y_MAX);
    }

    /**
     * 移动到边界时扣除移动步数，如仍需移动，则顺时针转向一次，继续往前移动，直到step=0
     * @param step
     * @param unmannedCar
     * @throws OutOfBorderException
     */
    @Override
    public void move(int step, UnmannedCar unmannedCar) throws OutOfBorderException {
        if (step == 0) return;
        System.out.println(String.format("{%s} move step {%d}", unmannedCar.toString(), step));
        if (unmannedCar.getCurrDirection().getValue() == GeoDirectionEnum.NORTH.getValue()) {
            int tempY = unmannedCar.getY() + step;
            if (tempY > this.getBORDER_Y_MAX()) {
                //throw new OutOfBorderException("Beyond the Y boundary");
                this.clockwiseRotation(unmannedCar);
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
                this.clockwiseRotation(unmannedCar);
                int remainingStep = step - unmannedCar.getY();
                unmannedCar.setY(this.getBORDER_Y_MIN());
                unmannedCar.autoMove(remainingStep, this);
            } else {
                unmannedCar.setY(tempY);
            }
        } else if (unmannedCar.getCurrDirection().getValue() == GeoDirectionEnum.EAST.getValue()) {
            int tempX = unmannedCar.getX() + step;
            if (tempX > this.getBORDER_X_MAX()) {
                this.clockwiseRotation(unmannedCar);
                int remainingStep = step - (this.getBORDER_X_MAX() - unmannedCar.getX());
                unmannedCar.setX(this.getBORDER_X_MAX());
                unmannedCar.autoMove(remainingStep, this);
            } else {
                unmannedCar.setX(tempX);
            }
        } else if (unmannedCar.getCurrDirection().getValue() == GeoDirectionEnum.WEST.getValue()) {
            int tempX = unmannedCar.getX() - step;
            if (tempX < this.getBORDER_X_MIN()) {
                this.clockwiseRotation(unmannedCar);
                int remainingStep = step - unmannedCar.getX();
                unmannedCar.setX(this.getBORDER_X_MIN());
                unmannedCar.autoMove(remainingStep, this);
            } else {
                unmannedCar.setX(tempX);
            }
        }
    }

    //顺时针移动
    private void clockwiseRotation(UnmannedCar unmannedCar) {
        GeoDirectionEnum newDirection = GeoDirectionEnum.getInstance((unmannedCar.getCurrDirection().getValue() + 1)%4);
        System.out.println(String.format("Beyond the boundary, turn automatically:{%s} -> {%s}",
                unmannedCar.getCurrDirection().getName(), newDirection.getName()));
        unmannedCar.turnDirection(newDirection);
    }

}
