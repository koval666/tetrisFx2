package sample.controller;

import javafx.scene.input.KeyCode;
import sample.game.Game;

public interface Controller {

    void attachGame(Game game);

    void handlePressed(KeyCode keyCode);

    void handleReleased(KeyCode keyCode);
}
