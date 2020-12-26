package sample.game.nes;

import sample.game.Action;
import sample.game.Direction;
import sample.game.Game;
import sample.game.nes.controlstate.ControlState;
import sample.game.nes.controlstate.impl.SimpleControlState;
import sample.ui.UI;

import static java.lang.Thread.interrupted;

public class NesGame implements Game {

    UI ui;

    ControlState controlState;
    Thread activeGameThread;

    @Override
    public void attachUI(UI ui) {
        if (ui != null) {
            throw new RuntimeException("UI already attached!");
        }
        this.ui = ui;
    }

    @Override
    public void rotate(Direction direction, Action action) {
        controlState.rotate(direction, action);
    }

    @Override
    public void moveFigure(Direction direction, Action action) {
        controlState.moveFigure(direction, action);

    }

    @Override
    public void dropFigureDown(Action action) {
        controlState.dropFigureDown(action);
    }

    @Override
    public void startNew() {
        if (activeGameThread != null) {
            activeGameThread.interrupt();
        }

        controlState = new SimpleControlState();
        activeGameThread = new Thread(() -> start(controlState));
        activeGameThread.setDaemon(true);

        activeGameThread.start();
    }

    private void start(ControlState state) {

        while (!interrupted()) {
            ControlState frameControls = state.createCopy();

            //game cycle

        }
    }
}
