package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import sample.controller.Controller;
import sample.controller.simpleimpl.SimpleController;
import sample.game.Game;
import sample.game.nes.NesGame;
import sample.ui.javafx.JavaFxUI;
import sample.ui.javafx.simpleui.SimpleJavaFxUI;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        JavaFxUI ui = createUi();
        Controller controller = createController();
        Game game = createGame();

        ui.attachController(controller);
        controller.attachGame(game);
        game.attachUI(ui);

        stage.setScene(ui.getScene());
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        stage.setTitle("Tetris in JavaFX");
        stage.show();

        game.startNew();
    }


    private JavaFxUI createUi() {
        return new SimpleJavaFxUI();
    }

    private Controller createController() {
        return new SimpleController();
    }

    private Game createGame() {
        return new NesGame();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
