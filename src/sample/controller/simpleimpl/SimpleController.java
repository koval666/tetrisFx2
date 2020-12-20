package sample.controller.simpleimpl;

import javafx.scene.input.KeyCode;
import sample.controller.Controller;
import sample.game.Game;

import static sample.game.Action.START;
import static sample.game.Action.STOP;
import static sample.game.Direction.LEFT;
import static sample.game.Direction.RIGHT;

public class SimpleController implements Controller {

    protected Game game;

    @Override
    public void attachGame(Game game) {
        this.game = game;
    }

    @Override
    public void handlePressed(KeyCode keyCode) {
        switch (keyCode) {
            case LEFT:
                game.moveFigure(LEFT, START);
                break;
            case RIGHT:
                game.moveFigure(RIGHT, START);
                break;
            case X:
                game.rotate(RIGHT, START);
                break;
            case Z:
                game.rotate(LEFT, START);
                break;
            case DOWN:
                game.dropFigureDown(START);
                break;
        }
    }

    @Override
    public void handleReleased(KeyCode keyCode) {
        switch (keyCode) {
            case LEFT:
                game.moveFigure(LEFT, STOP);
                break;
            case RIGHT:
                game.moveFigure(RIGHT, STOP);
                break;
            case X:
                game.rotate(RIGHT, STOP);
                break;
            case Z:
                game.rotate(LEFT, STOP);
                break;
            case DOWN:
                game.dropFigureDown(STOP);
                break;
        }
    }
}
