
import static Model.Level.Level.generateBoard;
import static View.GUI.Window.startGame;


public class Main {






    public static void main(String[] args) {
        System.out.println("Board");
        generateBoard();
        startGame(args);
    }


}
