package Model.Entities.Towers;

import Model.Entities.Entity;
import Model.Entities.Placeable;
import Model.Entities.Enemies.EnemyType;

public class Tower extends Entity implements Placeable {

    /**This is if the piece of terrain is already placed.*/
    private boolean myPlaced = false;
    /**This decides if the tower is currently selected by the player.*/
    private boolean mySelected = false;
    /**This is the damage the tower does, to enemies.*/
    private int myDamage;
    /**This is the cost to buy the tower.*/
    private int myCost;
    /**This is the cost to level up the tower.*/
    private int myLevelUpCost;
    /**This is the level of the tower, works as a multiplier for damage.*/
    private int myLevel = 0;
    /**This tower does double damage, to this enemy type.*/
    private EnemyType myAdvantage;


    /**
     * @param theTowerType This is the type of tower, that is being created.
     * @return The created tower.
     */
    public static Tower towerBuilder(final TowerType theTowerType) {
        Tower tower = new Tower();

        tower.myDamage = theTowerType.getDAMAGE();
        tower.myCost = theTowerType.getCOST();
        tower.myAdvantage = theTowerType.getADVANTAGE();

        return tower;
    }

    @Override
    public boolean isPlaced() {
        return myPlaced;
    }

    @Override
    public void setPlaced(final boolean thePlaced) {
        this.myPlaced = thePlaced;
    }

    @Override
    public void setMyXPos(final float myXPos) {
        if (!this.isPlaced() && this.mySelected) {
            super.setMyXPos(myXPos);
        }

    }
    @Override
    public void setMyYPos(final float myYPos) {
        if (!this.isPlaced() && this.mySelected) {
            super.setMyYPos(myYPos);
        }
    }


    /**
     * Setter method for the selected property.
     * @param theSelected Truthy value selected will be set to.
     */
    public void setSelected(final boolean theSelected) {
        mySelected = theSelected;
    }


    /**
     * This increase the towers level by 1.
     */
    public void levelUp () {
        this.myLevel += 1;
    }
}
