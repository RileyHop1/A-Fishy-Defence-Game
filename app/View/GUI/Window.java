package View.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Window  extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Hello, JavaFX!");
        Scene scene = new Scene(label, 1000, 1000);
        primaryStage.setTitle("Fishy defence game.");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**Launches the game, window
     * @param args Commandline arguments.
     */
    public static void startGame(String[] args) {
        launch(args);
    }

}
