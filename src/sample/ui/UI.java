package sample.ui;

import sample.controller.Controller;
import sample.game.BrickTable;

public interface UI {

    void attachController(Controller controller);

    void updateCup(BrickTable brickTable);

    void updateHint(BrickTable brickTable);

    void setScore(long score);

    void setLines(long lines);

    void setLevel(long level);

    void gameOver();
}
