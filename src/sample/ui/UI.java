package sample.ui;

import sample.controller.Controller;
import sample.game.BrickTable;

public interface UI {

    void attachController(Controller controller);

    void repaintCup(BrickTable brickTable);

    void repaintHint(BrickTable brickTable);

    void setScore(long score);

    void setLines(long lines);

    void setLevel(long level);

    void gameOver();
}
