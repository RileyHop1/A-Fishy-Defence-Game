package Model.Entities.Terrain;

import Model.Entities.Entity;
import Model.Entities.Placeable;

public class Terrain extends Entity implements Placeable {

    @Override
    public void place() {

    }

    @Override
    public boolean isPlaced() {
        return false;
    }
}
