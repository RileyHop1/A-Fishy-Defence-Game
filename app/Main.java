import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static Model.Level.Level.generateBoard;


public class Main extends Application {






    public static void main(String[] args) {
        System.out.println("Board");
        generateBoard();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Hello, JavaFX!");
        Scene scene = new Scene(label, 1000, 1000);
        primaryStage.setTitle("Fishy defence game.");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
