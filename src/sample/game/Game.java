package sample.game;

import sample.ui.UI;

public interface Game {

    void attachUI(UI ui);

    void rotate(Direction direction, Action action);

    void moveFigure(Direction direction, Action action);

    void dropFigureDown(Action action);
}
