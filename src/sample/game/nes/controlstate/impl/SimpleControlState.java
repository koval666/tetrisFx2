package sample.game.nes.controlstate.impl;

import sample.game.Action;
import sample.game.Direction;
import sample.game.nes.controlstate.ControlState;

import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleControlState implements ControlState {

    private final AtomicBoolean isRotatingToLeft = new AtomicBoolean(false);
    private final AtomicBoolean isRotatingToRight = new AtomicBoolean(false);

    private final AtomicBoolean isMovingToLeft = new AtomicBoolean(false);
    private final AtomicBoolean isMovingToRight = new AtomicBoolean(false);

    private final AtomicBoolean isDroppingDown = new AtomicBoolean(false);


    public SimpleControlState() {
    }

    private SimpleControlState(SimpleControlState controlState) {
        this.isRotatingToLeft.set(controlState.isRotatingToLeft.get());
        this.isRotatingToRight.set(controlState.isRotatingToRight.get());
        this.isMovingToLeft.set(controlState.isMovingToLeft.get());
        this.isMovingToRight.set(controlState.isMovingToRight.get());
        this.isDroppingDown.set(controlState.isDroppingDown.get());
    }

    @Override
    public void rotate(Direction direction, Action action) {
        switch (direction) {
            case LEFT:
                isRotatingToLeft.set(isStart(action));
                break;
            case RIGHT:
                isRotatingToRight.set(isStart(action));
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void moveFigure(Direction direction, Action action) {
        switch (direction) {
            case LEFT:
                isMovingToLeft.set(isStart(action));
                break;
            case RIGHT:
                isMovingToRight.set(isStart(action));
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void dropFigureDown(Action action) {
        isDroppingDown.set(isStart(action));
    }

    private boolean isStart(Action action) {
        return Action.START.equals(action);
    }

    @Override
    public ControlState createCopy() {
        return new SimpleControlState(this);
    }
}
