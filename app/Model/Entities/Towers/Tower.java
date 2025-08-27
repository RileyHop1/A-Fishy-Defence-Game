package Model.Entities.Towers;

import Model.Entities.Entity;
import Model.Entities.Placeable;

public class Tower extends Entity implements Placeable {

    /**This is if the piece of terrain is already placed.*/
    private boolean myPlaced = false;

    @Override
    public boolean isPlaced() {
        return myPlaced;
    }

    @Override
    public void setPlaced(final boolean thePlaced) {
        this.myPlaced = thePlaced;
    }
}
