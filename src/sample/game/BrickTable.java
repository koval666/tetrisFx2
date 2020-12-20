package sample.game;

import java.awt.*;

public interface BrickTable {

    int getVisibleWidth();

    int getVisibleHeight();

    Brick getBrick(Point point);

    boolean hasBrick(Point point);

    void putBrick(Brick brick, Point point);

    void removeBrick(Point point);

}
