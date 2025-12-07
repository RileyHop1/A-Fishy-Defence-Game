package View.GUI;

import Model.Engine.ObserverPattern.ObserverRH;
import Model.Engine.ObserverPattern.PropertyChangeEvents.LevelProperties;
import Model.Level.Level;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;

import static View.GUI.Srites.ICON;

public class Window  extends Application implements ObserverRH {

    /**This is the title of the game.*/
    private static final String TITLE = "Fishy defence game";

    private static final int TILE_SIZE = 16;

    private static final String myMainMenuBackground =
            "Sprites/terrain/TitleScreen/Main_Menu.png";

    private static final String myButtonStyle =
            "-fx-background-color: #3498db; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 16px; " +
            "-fx-background-radius: 5px;";

    private static final String myButtonStyleHover =
                    "-fx-background-color: #00D3FF; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-size: 20px; " +
                    "-fx-background-radius: 5px;";


    private static final int  TEST_DIMESNSION = 600;

    private Scene myMainMenuScene;

    private Scene mySettingsScene;

    private Scene myGameScene;

    private Stage myWindow;

    private final HBox mySettingsRoot = new HBox();

    private final StackPane myMainMenuRoot = new StackPane();

    private final Pane myGameRoot = new Pane();

    private final Canvas myBoard = new Canvas();

    @Override
    public void start(Stage primaryStage) {


        myWindow = primaryStage;

        initialSetUp();
        Image icon = new Image(ICON.getFilePath("Random"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle(TITLE);


        primaryStage.setScene(myMainMenuScene);

        primaryStage.show();

    }

    /**Launches the game, window
     * @param args Commandline arguments.
     */
    public void startGame(String[] args) {
        Level.getInstance().attachObserverRH(this);
        this.launch(args);
    }



    /**
     * This does the initial scene setup.
     */
    private void initialSetUp() {


        Image backGround = new Image(myMainMenuBackground);

        //Sets up the background
        BackgroundImage backgroundImage = new BackgroundImage(
                backGround
                , BackgroundRepeat.NO_REPEAT
                , BackgroundRepeat.NO_REPEAT
                , BackgroundPosition.CENTER
                , new BackgroundSize(
                100, 100,
                true, true,
                false, true)
                );

        //This sets up all the roots for the scenes
        myGameScene = new Scene(myGameRoot, TEST_DIMESNSION, TEST_DIMESNSION);
        myMainMenuScene = new Scene(myMainMenuRoot, TEST_DIMESNSION, TEST_DIMESNSION);
        mySettingsScene = new Scene(mySettingsRoot, TEST_DIMESNSION, TEST_DIMESNSION);

        Button playButton = new Button("Play Game");
        Button settingsButton = new Button("Settings");
        Button exitButton = new Button("Exit");

        //Sets the base styling for the buttons
        playButton.setStyle(myButtonStyle);
        settingsButton.setStyle(myButtonStyle);
        exitButton.setStyle(myButtonStyle);

        playButton.setOnMouseEntered(e ->
                playButton.setStyle(myButtonStyleHover));
        settingsButton.setOnMouseEntered(e ->
                settingsButton.setStyle(myButtonStyleHover));
        exitButton.setOnMouseEntered(e ->
                exitButton.setStyle(myButtonStyleHover));

        playButton.setOnMouseExited(e ->
                playButton.setStyle(myButtonStyle));
        settingsButton.setOnMouseExited(e ->
                settingsButton.setStyle(myButtonStyle));
        exitButton.setOnMouseExited(e ->
                exitButton.setStyle(myButtonStyle));

        playButton.setOnAction(e ->
                myWindow.setScene(myGameScene));
        settingsButton.setOnAction(e ->
                myWindow.setScene(mySettingsScene));
        exitButton.setOnAction(e ->
                myWindow.close());

        VBox menuBox = new VBox(20, playButton, settingsButton, exitButton);
        menuBox.setAlignment(Pos.CENTER);
        myMainMenuRoot.setBackground(new Background(backgroundImage));

        myMainMenuRoot.getChildren().add(menuBox);

    }

    private void drawBoard() {
        Level level = Level.getInstance();
        String[][] map = level.getBoard();

        myBoard.setHeight(level.getMapSize() * TILE_SIZE);
        myBoard.setWidth(level.getMapSize() * TILE_SIZE);

        GraphicsContext gc = myBoard.getGraphicsContext2D();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                Image img = new Image(
                        ICON.getFilePath(map[i][j]));
                double x = j * TILE_SIZE, y = i * TILE_SIZE;

                gc.drawImage(img, x, y, TILE_SIZE, TILE_SIZE);

            }
        }

        myGameRoot.getChildren().add(myBoard);



    }


    @Override
    public void updateRH(final String theUpdate) {
        switch (theUpdate) {

            case LevelProperties.myMapUpdated:
                drawBoard();
                break;


        }

    }
}
