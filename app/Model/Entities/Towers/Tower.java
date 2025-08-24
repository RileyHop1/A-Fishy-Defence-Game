package Model.Entities.Towers;

import Model.Entities.Entity;
import Model.Entities.Placeable;

public class Tower extends Entity implements Placeable {

    @Override
    public void place() {

    }

    @Override
    public boolean isPlaced() {
        return false;
    }
}
