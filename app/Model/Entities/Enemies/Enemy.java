package Model.Entities.Enemies;

import Model.Entities.Type;

import java.util.ArrayList;

public class Enemy {

    private int myHealth;
    private int mySpeed;
    /**This is the terrain type the enemy can move through.*/
    private ArrayList<Type> myVaildPath = new ArrayList<>();

    private Enemy() {
        super();
    }

    public int getMyHealth() {
        return myHealth;
    }

    public void setMyHealth(int myHealth) {
        this.myHealth = myHealth;
    }

    public int getMySpeed() {
        return mySpeed;
    }

    public void setMySpeed(int mySpeed) {
        this.mySpeed = mySpeed;
    }

    /**
     * This moves the enemy further down the path.
     */
    public static void move() {

    }


}
