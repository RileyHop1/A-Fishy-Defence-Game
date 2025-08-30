package Model.Entities.Towers;

import Model.Entities.Enemies.EnemyType;

public enum TowerType {
    ICE(200,50,EnemyType.FIRE),
    ROCK(500,300,EnemyType.ICE),
    FIRE(750,100,EnemyType.AIR),
    AIR(500, 100,EnemyType.ROCK),
    CORAL(100, 100, EnemyType.CORAL);

    private final int COST;
    private final int DAMAGE;
    private final EnemyType ADVANTAGE;

    TowerType(final int theCost, final int theDamage
            , final EnemyType theType) {
        this.COST = theCost;
        this.DAMAGE = theDamage;
        this.ADVANTAGE = theType;
    }

    //These are the affects each tower type applies when it attacks.
    //TODO: Implement damage affects.
    private final String SLOWED = "Slowed";
    private final String ONFIRE = "Burning";
    private final String PUSHEDBACK = "Knocked";

    public void getAffect() {


    }

    public int getDAMAGE() {
        return DAMAGE;
    }

    public int getCOST() {
        return COST;
    }

    public EnemyType getADVANTAGE() {
        return ADVANTAGE;
    }
}
