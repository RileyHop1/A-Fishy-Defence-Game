package Model.Entities;

import Model.Entities.Enemies.EnemyType;

public class Entity {

    /**This, the x cord of the top left corner.*/
   private float myXPos;
    /**This, the y cord of the top left corner.*/
   private float myYPos;
   /**This is the height of the entity.*/
   private float myHeight;
   /**This is the width of the entity.*/
   private float myWidth;
   /**This is the name of the entity. */
   private String myName;
    /**This is the element of the entity.*/
    private EnemyType myEnemyType;



    public float getMyXPos() {
        return myXPos;
    }

    public void setMyXPos(final float myXPos) {
        this.myXPos = myXPos;
    }

    public float getMyYPos() {
        return myYPos;
    }

    public void setMyYPos(final float myYPos) {
        this.myYPos = myYPos;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(final String myName) {
        this.myName = myName;
    }

    public float getMyHeight() {
        return myHeight;
    }

    public void setMyHeight(final float myHeight) {
        this.myHeight = myHeight;
    }

    public float getMyWidth() {
        return myWidth;
    }

    public void setMyWidth(final float myWidth) {
        this.myWidth = myWidth;
    }

    public EnemyType getMyType() {
        return myEnemyType;
    }

    public void setMyType(EnemyType myEnemyType) {
        this.myEnemyType = myEnemyType;
    }
}
