package sample.game.nes.dictionary;


import sample.Configuration;

import java.util.HashMap;
import java.util.Map;

public class SpeedDictionary {

    private Map<Integer, Integer> dictionary = new HashMap<>();

    public SpeedDictionary() {
        for (int i = 0; i < 9; i++) {
            dictionary.put(i, 48 - (i * 5));
        }

        dictionary.put(9, 6);

        dictionary.put(10, 5);
        dictionary.put(11, 5);
        dictionary.put(12, 5);

        dictionary.put(13, 4);
        dictionary.put(14, 4);
        dictionary.put(15, 4);

        dictionary.put(16, 3);
        dictionary.put(17, 3);
        dictionary.put(18, 3);

        for (int i = 19; i < 29; i++) {
            dictionary.put(i, 2);
        }
    }

    public int getFramePerCell(int level) {
        if (level > 28)
            return 1;
        else
            return dictionary.get(level);
    }

    public int getDropDownFramePerCell() {
        return 2;
    }

    public int getMovingStartDelay() {
        return 16;
    }

    public int getMovingDelay() {
        return 6;
    }

    public int getSpawnDelay(int lowerBrickY) {
        if (lowerBrickY >= Configuration.VISIBLE_HEIGHT_BRICKS - 2) {
            return 10;
        } else if (lowerBrickY >= Configuration.VISIBLE_HEIGHT_BRICKS - 2 - 4) {
            return 12;
        } else if (lowerBrickY >= Configuration.VISIBLE_HEIGHT_BRICKS - 2 - 4 * 2) {
            return 14;
        } else if (lowerBrickY >= Configuration.VISIBLE_HEIGHT_BRICKS - 2 - 4 * 3) {
            return 16;
        } else if (lowerBrickY >= Configuration.VISIBLE_HEIGHT_BRICKS - 2 - 4 * 4) {
            return 18;
        } else {
            return 18;
        }
    }

    //todo выяснить значение задержки в nes
    public int getFirstDroppingDelay() {
        return 60;
    }
}
