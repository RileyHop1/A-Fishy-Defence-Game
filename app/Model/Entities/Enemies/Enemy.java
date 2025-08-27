package Model.Entities.Enemies;

import Model.Entities.Entity;
import Model.Entities.Type;

import java.util.ArrayList;

public class Enemy extends Entity {

    private int myHealth;
    private float mySpeed;
    /**This is the terrain type the enemy can move through.*/
    private ArrayList<Type> myVaildPath = new ArrayList<>();


    /**
     * This constructs, a new enemy.
     */
    private Enemy() {
        super();

    }


    /**
     * This constructs a new enemy relative to the difficulty and
     * the round. It also makes it depend on the type.
     * @param theType this is the type of the hook.
     * @param theDif this is the difficulty setting.
     * @param theRound this is the round the player is on.
     * @return The newly constructed Enemy.
     */
    public static Enemy enemyCreator(final Type theType, final int theDif
            , final int theRound) {

        //May need some tweaks.
        int difficultyMultiplier = theDif * theRound;

        Enemy enemy = new Enemy();

        //Coral is the default type, and the sand type should never be given to a monster.
        switch (theType) {
            case Type.ICE:
                enemy.myVaildPath.add(Type.CORAL);
                enemy.myVaildPath.add(Type.ICE);
                enemy.setMyHealth(theType.getHEALTH()
                        * difficultyMultiplier);
                enemy.setMySpeed(theType.getSPEED()
                        * difficultyMultiplier);
                break;
            case Type.ROCK:
                enemy.myVaildPath.add(Type.CORAL);
                enemy.myVaildPath.add(Type.ROCK);
                enemy.setMyHealth(theType.getHEALTH()
                        * difficultyMultiplier);
                enemy.setMySpeed(theType.getSPEED()
                        * difficultyMultiplier);
                break;
            case Type.FIRE:
                enemy.myVaildPath.add(Type.CORAL);
                enemy.myVaildPath.add(Type.FIRE);
                enemy.setMyHealth(theType.getHEALTH()
                        * difficultyMultiplier);
                enemy.setMySpeed(theType.getSPEED()
                        * difficultyMultiplier);
                break;
            case Type.AIR:
                enemy.myVaildPath.add(Type.CORAL);
                enemy.myVaildPath.add(Type.AIR);
                enemy.setMyHealth(theType.getHEALTH()
                        * difficultyMultiplier);
                enemy.setMySpeed(theType.getSPEED()
                        * difficultyMultiplier);
                break;
            default:
                enemy.myVaildPath.add(Type.CORAL);
                enemy.setMyHealth(Type.CORAL.getHEALTH()
                        * difficultyMultiplier);
                enemy.setMySpeed(Type.CORAL.getSPEED()
                        * difficultyMultiplier);
                break;
        }


        //Returns the new enemy
        return enemy;

    }

    public int getMyHealth() {
        return myHealth;
    }

    public void setMyHealth(final int myHealth) {
        this.myHealth = myHealth;
    }

    public float getMySpeed() {
        return mySpeed;
    }

    public void setMySpeed(final float mySpeed) {
        this.mySpeed = mySpeed;
    }

    /**
     * This moves the enemy further down the path.
     */
    public static void move() {

        //TODO: implement the level.
    }


}
