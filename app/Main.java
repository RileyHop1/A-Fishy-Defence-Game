
import Model.Level.Level;
import View.GUI.Window;



public class Main {






    public static void main(String[] args) {
        Level level = Level.getInstance();
        Window window = new Window();
        System.out.println("Board");
        level.generateBoard();
        window.startGame(args);
    }


}
