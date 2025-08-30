package Model.Entities.Enemies;

import Model.Entities.Entity;

public class Enemy extends Entity {

    private int myHealth;
    private float mySpeed;



    /**
     * This constructs, a new enemy.
     */
    private Enemy() {
        super();

    }


    /**
     * This constructs a new enemy relative to the difficulty and
     * the round. It also makes it depend on the type.
     * @param theEnemyType this is the type of the hook.
     * @param theDif this is the difficulty setting.
     * @param theRound this is the round the player is on.
     * @return The newly constructed Enemy.
     */
    public static Enemy monsterMaker(final EnemyType theEnemyType, final int theDif
            , final int theRound) {

        //May need some tweaks.
        int difficultyMultiplier = theDif * theRound;

        Enemy enemy = new Enemy();

        enemy.setMyHealth(theEnemyType.getHEALTH()
                * difficultyMultiplier);
        enemy.setMySpeed(theEnemyType.getSPEED()
                * difficultyMultiplier);

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
