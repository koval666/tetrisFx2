package sample.game.nes.controlstate;

import sample.game.Action;
import sample.game.Direction;

public interface ControlState {

    void rotate(Direction direction, Action action);

    void moveFigure(Direction direction, Action action);

    void dropFigureDown(Action action);

    ControlState createCopy();

    boolean isRotating(Direction direction);

    boolean isMoving(Direction direction);

    boolean isDroppingDown();
}
