package Model.Entities.Enemies;

import Model.Entities.Entity;
import Model.Entities.Type;

import java.util.ArrayList;

public class Enemy extends Entity {

    private int myHealth;
    private int mySpeed;
    /**This is the terrain type the enemy can move through.*/
    private ArrayList<Type> myVaildPath = new ArrayList<>();


    /**
     * @param theHealth This is the health of the enemy.
     * @param theSpeed This is the speed of the enemy.
     * @param theType This is the element of the enemy,
     *                different element have different strengths
     *                and weaknesses and paths they can take.
     * @param theName This is the name of the enemy.
     */
    private Enemy(final int theHealth, final int theSpeed
            , final Type theType, final String theName) {
        super();

        myHealth = theHealth;
        mySpeed = theSpeed;
        this.setMyType(theType);

    }


    /**
     * @param theType
     * @param theDif
     * @param theRound
     * @return
     */
    public Enemy enemyCreator(final Type theType, final int theDif
            , final int theRound) {
        switch (theType) {
            case Type.ICE:
                myVaildPath.add(Type.ICE);
                break;
            case Type.ROCK:
                myVaildPath.add(Type.ROCK);
                break;
            case Type.FIRE:
                myVaildPath.add(Type.FIRE);
                break;
            case Type.AIR:
                myVaildPath.add(Type.AIR);
                break;
            case Type.SAND:
                myVaildPath.add(Type.SAND);
                break;
            case Type.CORAL:
                myVaildPath.add(Type.CORAL);
                break;

        }

        //this is a placeholder.
        return new Enemy(0,0,Type.AIR,"1");

    }

    public int getMyHealth() {
        return myHealth;
    }

    public void setMyHealth(final int myHealth) {
        this.myHealth = myHealth;
    }

    public int getMySpeed() {
        return mySpeed;
    }

    public void setMySpeed(final int mySpeed) {
        this.mySpeed = mySpeed;
    }

    /**
     * This moves the enemy further down the path.
     */
    public static void move() {

    }


}
