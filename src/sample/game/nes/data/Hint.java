package sample.game.nes.data;

import sample.game.Brick;
import sample.game.BrickTable;

import java.awt.*;

public class Hint implements BrickTable {
    @Override
    public int getVisibleWidth() {
        return 0;
    }

    @Override
    public int getVisibleHeight() {
        return 0;
    }

    @Override
    public Brick getBrick(Point point) {
        return null;
    }

    @Override
    public boolean hasBrick(Point point) {
        return false;
    }

    @Override
    public void putBrick(Brick brick, Point point) {

    }

    @Override
    public void removeBrick(Point point) {

    }
}
