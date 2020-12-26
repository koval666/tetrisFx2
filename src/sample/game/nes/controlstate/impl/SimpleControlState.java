package sample.game.nes.controlstate.impl;

import sample.game.Action;
import sample.game.Direction;
import sample.game.nes.controlstate.ControlState;

public class SimpleControlState implements ControlState {

    @Override
    public void rotate(Direction direction, Action action) {

    }

    @Override
    public void moveFigure(Direction direction, Action action) {

    }

    @Override
    public void dropFigureDown(Action action) {

    }

    @Override
    public ControlState createCopy() {
        return null;
    }
}
