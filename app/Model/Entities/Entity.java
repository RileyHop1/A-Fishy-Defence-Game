package Model.Entities;

public class Entity {

    /**This, the x cord of the top left corner.*/
   private float myXPos;
    /**This, the y cord of the top left corner.*/
   private float myYpos;
   /**This is the height of the entity.*/
   private float myHeight;
   /**This is the width of the entity.*/
   private float myWidth;
   /**This is the name of the entity. */
   private String myName;


    public float getMyXPos() {
        return myXPos;
    }

    public void setMyXPos(final float myXPos) {
        this.myXPos = myXPos;
    }

    public float getMyYpos() {
        return myYpos;
    }

    public void setMyYpos(final float myYpos) {
        this.myYpos = myYpos;
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
}
