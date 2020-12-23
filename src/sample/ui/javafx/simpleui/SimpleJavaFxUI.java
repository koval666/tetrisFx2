package sample.ui.javafx.simpleui;

import com.sun.istack.internal.NotNull;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.controller.Controller;
import sample.game.Brick;
import sample.game.BrickTable;
import sample.ui.javafx.JavaFxUI;

import java.awt.*;
import java.util.concurrent.locks.ReentrantLock;

import static sample.Configuration.*;

public class SimpleJavaFxUI implements JavaFxUI {

    private final int SCENE_WIDTH_PX = 315;
    private final int SCENE_HEIGHT_PX = 500;

    private final int BRICK_WIDTH_PX = 20;
    private final int BRICK_HEIGHT_PX = 20;

    private final Color DEFAULT_COLOR = Color.BLACK;
    private final Color STROKE_COLOR = Color.BLACK;

    private final int PADDING_PX = 5;

    private final String TEXT_STYLE = "-fx-font-size:20px;";


    protected final Scene scene;
    protected final GridPane cupGrid;
    protected final GridPane hintGrid;
    protected final Label scoreLabel;
    protected final Label linesLabel;
    protected final Label levelLabel;

    ReentrantLock cupUpdateLock = new ReentrantLock();
    ReentrantLock hintUpdateLock = new ReentrantLock();


    public SimpleJavaFxUI() {
        cupGrid = new GridPane();
        cupGrid.setGridLinesVisible(true);

        for (int y = 0; y < VISIBLE_HEIGHT_BRICKS; y++) {
            for (int x = 0; x < VISIBLE_WIDTH_BRICKS; x++) {
                Rectangle rectangle = new Rectangle(x, y, BRICK_WIDTH_PX, BRICK_HEIGHT_PX);
                rectangle.setFill(DEFAULT_COLOR);
                rectangle.setStroke(STROKE_COLOR);
                cupGrid.add(rectangle, x, y);
            }
        }


        hintGrid = new GridPane();
        hintGrid.setGridLinesVisible(true);

        for (int y = 0; y < HINT_VISIBLE_HEIGHT_BRICKS; y++) {
            for (int x = 0; x < HINT_VISIBLE_WIDTH_BRICKS; x++) {
                Rectangle rectangle = new Rectangle(x, y, BRICK_WIDTH_PX, BRICK_HEIGHT_PX);
                rectangle.setFill(DEFAULT_COLOR);
                rectangle.setStroke(STROKE_COLOR);
                hintGrid.add(rectangle, x, y);
            }
        }

        scoreLabel = new Label();
        scoreLabel.setPadding(new Insets(PADDING_PX));
        scoreLabel.setStyle(TEXT_STYLE);
        linesLabel = new Label();
        linesLabel.setPadding(new Insets(PADDING_PX));
        linesLabel.setStyle(TEXT_STYLE);
        levelLabel = new Label();
        levelLabel.setPadding(new Insets(PADDING_PX));
        levelLabel.setStyle(TEXT_STYLE);

        Pane rightBox = new VBox(hintGrid, scoreLabel, linesLabel, levelLabel);
        Pane root = new HBox(cupGrid, rightBox);
        cupGrid.setPadding(new Insets(PADDING_PX));
        hintGrid.setPadding(new Insets(PADDING_PX));


        scene = new Scene(root, SCENE_WIDTH_PX, SCENE_HEIGHT_PX);
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    public void attachController(Controller controller) {
        scene.addEventHandler(KeyEvent.ANY, new HoldingWithoutRepeatEventHandler() {
            @Override
            public void handlePressed(KeyCode keyCode) {
                controller.handlePressed(keyCode);
            }

            @Override
            public void handleReleased(KeyCode keyCode) {
                controller.handleReleased(keyCode);
            }
        });
    }

    @Override
    public void updateCup(BrickTable brickTable) {
        if (cupUpdateLock.tryLock()) {
            try {
                repaintBrickGrid(cupGrid, brickTable);
            } finally {
                cupUpdateLock.unlock();
            }
        }
    }

    @Override
    public void updateHint(BrickTable brickTable) {
        if (hintUpdateLock.tryLock()) {
            try {
                repaintBrickGrid(hintGrid, brickTable);
            } finally {
                hintUpdateLock.unlock();
            }
        }

    }

    /**
     * Point 0:0 is in upper left corner
     */
    private void repaintBrickGrid(GridPane grid, BrickTable brickTable) {
        for (int y = 0; y < brickTable.getVisibleHeight(); y++) {
            for (int x = 0; x < brickTable.getVisibleWidth(); x++) {
                Point currentPoint = new Point(x, y);

                if (brickTable.hasBrick(currentPoint)) {
                    Brick brick = brickTable.getBrick(currentPoint);
                    getRectangle(grid, currentPoint).setFill(brick.getColor());
                } else {
                    getRectangle(grid, currentPoint).setFill(DEFAULT_COLOR);
                }
            }
        }
    }

    private Rectangle getRectangle(@NotNull GridPane rectangleGrid, Point point) {
        Node result = null;
        ObservableList<Node> childrens = rectangleGrid.getChildren();

        for (Node node : childrens) {
            if (GridPane.getRowIndex(node) != null
                    && GridPane.getRowIndex(node) == point.getY()
                    && GridPane.getColumnIndex(node) != null
                    && GridPane.getColumnIndex(node) == point.getX()) {
                result = node;
                break;
            }
        }

        return (Rectangle) result;
    }

    @Override
    public void setScore(long score) {
        Platform.runLater(() -> {
            scoreLabel.setText(String.format("%06d", score));
        });
    }

    @Override
    public void setLines(long lines) {
        Platform.runLater(() -> {
            linesLabel.setText(String.format("%03d", lines));
        });

    }

    @Override
    public void setLevel(long level) {
        Platform.runLater(() -> {
            levelLabel.setText(String.format("%02d", level));
        });
    }

    @Override
    public void gameOver() {
        System.out.println("Game over!");
    }
}
