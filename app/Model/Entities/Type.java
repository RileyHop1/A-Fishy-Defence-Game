package Model.Entities;

import javafx.scene.Scene;

public enum Type {
    //Basically randomly guessing, needs some balance.
    ICE(20,11.0f),
    ROCK(500,1.0f),
    FIRE(75,6.0f),
    AIR(50, 7.0f),
    SAND(0, 0.0f),
    CORAL(100, 5.0f);

    private final int HEALTH;
    private final float SPEED;

    //These are the names for the types.
    private static final String SANDNAME = "Sand";
    private static final String ICENAME = "Ice";
    private static final String ROCKNAME = "Rock";
    private static final String FIRENAME = "Fire";
    private static final String AIRNAME = "Air";
    private static final String CORALNAME = "Coral";

    Type(final int theHealth, final float theSpeed) {
        this.HEALTH = theHealth;
        this.SPEED = theSpeed;
    }

    public int getHEALTH(){
        return HEALTH;
    }

    public float getSPEED() {
        return SPEED;
    }

    public static String getICENAME() {
        return ICENAME;
    }

    public static String getROCKNAME() {
        return ROCKNAME;
    }

    public static String getSANDNAME() {
        return SANDNAME;
    }

    public static String getFIRENAME() {
        return FIRENAME;
    }

    public static String getAIRNAME() {
        return AIRNAME;
    }

    public static String getCORALNAME() {
        return CORALNAME;
    }

}
