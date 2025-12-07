package Model.Entities.Enemies;


public enum EnemyType {
    //Basically randomly guessing, needs some balance.
    ICE(20,11.0f),
    ROCK(500,1.0f),
    FIRE(75,6.0f),
    AIR(50, 7.0f),
    CORAL(100, 5.0f);

    private final int HEALTH;
    private final float SPEED;

    EnemyType(final int theHealth, final float theSpeed) {
        this.HEALTH = theHealth;
        this.SPEED = theSpeed;
    }

    public int getHEALTH(){
        return HEALTH;
    }

    public float getSPEED() {
        return SPEED;
    }



}
