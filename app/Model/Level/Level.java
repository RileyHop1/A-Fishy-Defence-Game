package Model.Level;

import Model.Entities.Terrain.Terrain;
import java.util.concurrent.ThreadLocalRandom;

public class Level {

    /**This is the size of the board that will be played on,
     * the number is equivalent to one tile for either like terrain or a tower.*/
    private static int myMapSize = 0;
    /**This is the serialized version of the board.*/
    private static Terrain[][] myBoard;
    /**This is the make sure the spawn point has been placed first.*/
    private static boolean mySpawnPointChosen = false;
    /**This the entry point of hooks.*/
    private static int[] myStartPos = new int[2];
    /**This is the exit point for the hooks.*/
    private static int[] myEndPos = new int[2];


    /**
     * @param theMapSize This is the size of the map you want.
     */
    public static void chooseMapSize(final int theMapSize) {
        myMapSize = theMapSize;
    }

    /**
     * This generates a new board to player on.
     */
    public static void generateBoard() {



    }

    /**
     * This chooses a random start position.
     */
    private static void setRandomStart() {
        if (myMapSize < 10) {
            throw new RuntimeException("Map size must be greater than or equal to 10");
        }
        ThreadLocalRandom myRand = ThreadLocalRandom.current();
        myStartPos[1] = myRand.nextInt(myMapSize);

        //This makes sure the start is always on the border
        if (myStartPos[1] == 0 || myStartPos[1] == myMapSize - 1 ) {
            myStartPos[0] = myRand.nextInt(myMapSize);
        } else {
            if(myRand.nextBoolean()) {
                myStartPos[0] = 0;
            } else {
                myStartPos[0] = myMapSize - 1;
            }

        }
        mySpawnPointChosen = true;
    }

    /**
     * This creates an endPoint on the opposite side from the spawn point.
     * This should be called after a spawn point has been chosen.
     */
    private static void setRandomEnd() {
        if (!mySpawnPointChosen) {
            throw new RuntimeException("A start position must be chosen first.");
        }

        ThreadLocalRandom myRand = ThreadLocalRandom.current();
        int endPos = myMapSize - 1;

        if (myStartPos[1] == 0) {
            myEndPos[1] = endPos;
        } else if (myStartPos[1] == endPos) {
            myEndPos[1] = 0;
        } else {
            myEndPos[1] = myRand.nextInt(1, endPos);
        }

        if (myEndPos[1] == 0 || myEndPos[1] == endPos) {
            myEndPos[0] = myRand.nextInt(myMapSize);
        } else {
            if(myRand.nextBoolean()) {
                myEndPos[0] = 0;
            } else {
                myEndPos[0] = myMapSize - 1;
            }

        }
    }









}
