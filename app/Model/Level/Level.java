package Model.Level;

import Model.Entities.Terrain.Terrain;
import Model.Entities.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Level {

    /**This is the size of the board that will be played on,
     * the number is equivalent to one tile for either like terrain or a tower.*/
    private static int myMapSize = 0;
    /**This is the serialized version of the board.*/
    private static String[][] myBoard;
    /**This is the make sure the spawn point has been placed first.*/
    private static boolean mySpawnPointChosen = false;
    /**This the entry point of hooks.*/
    private static int[] myStartPos = new int[2];
    /**This is the exit point for the hooks.*/
    private static int[] myEndPos = new int[2];
    /**This is a thread safe, random.*/
    private static ThreadLocalRandom myRand = ThreadLocalRandom.current();


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
        setRandomStart();
        setRandomEnd();



    }

    /**
     * This chooses a random start position.
     */
    private static void setRandomStart() {
        if (myMapSize < 10) {
            throw new RuntimeException("Map size must be greater " +
                    "than or equal to 10");
        }

        myStartPos[1] = myRand.nextInt(myMapSize);

        //This makes sure the start is always on the border
        if (myStartPos[1] == 0 || myStartPos[1]
                == myMapSize - 1 ) {
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
            throw new RuntimeException("A start position " +
                    "must be chosen first.");
        }

        int endPos = myMapSize - 1;

        //This will force the end to be on the opposite side.
        if (myStartPos[1] == 0) {
            myEndPos[1] = endPos;
        } else if (myStartPos[1] == endPos) {
            myEndPos[1] = 0;
        } else {
            myEndPos[1] = myRand.nextInt(1, endPos);
        }

        //This makes sure the position stays on the edge.
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

    private static void generatePath(final int[] theStartingPos) {

        if (theStartingPos.length != 2) {
            throw new RuntimeException("The starting pos must " +
                    "be an array of length 2");
        }

        ArrayList<int[]> pathTaken = new ArrayList<>();

        //grabs the current position.
        int[] currentPos = Arrays.copyOf(theStartingPos,theStartingPos.length);



        while (currentPos[0] != myEndPos[0]
                && currentPos[1] != myEndPos[1]) {

            //This will randomly choose a direction as noise 1/5th and also check if its sand.
            if (myRand.nextInt(20) == 0 && currentPos[0] + 1 < myMapSize &&
                    Objects.equals(myBoard[currentPos[0] + 1]
                            [currentPos[1]], Type.getSANDNAME())) {
                currentPos[0]++;
                pathTaken.add(Arrays.copyOf(currentPos, currentPos.length));
                continue;
            } else if (myRand.nextInt(20) == 0 && currentPos[1] + 1 < myMapSize &&
                    Objects.equals(myBoard[currentPos[0]]
                            [currentPos[1] + 1], Type.getSANDNAME())) {
                currentPos[1]++;
                pathTaken.add(Arrays.copyOf(currentPos, currentPos.length));
                continue;
            } else if (myRand.nextInt(20) == 0 && currentPos[0] - 1 >= 0 &&
                    Objects.equals(myBoard[currentPos[0] - 1]
                            [currentPos[1]], Type.getSANDNAME())) {
                currentPos[0]--;
                pathTaken.add(Arrays.copyOf(currentPos, currentPos.length));
                continue;
            } else if (myRand.nextInt(20) == 0 && currentPos[1] - 1 >= 0 &&
                    Objects.equals(myBoard[currentPos[0]]
                            [currentPos[1] - 1], Type.getSANDNAME())) {
                currentPos[1]--;
                pathTaken.add(Arrays.copyOf(currentPos, currentPos.length));
                continue;
            }

            if (currentPos[0] < myEndPos[0] &&
                    Objects.equals(myBoard[currentPos[0]]
                            [currentPos[1] - 1], Type.getSANDNAME())) {

                currentPos[0]++;

            } else if (currentPos[0] > myEndPos[0] &&
                    Objects.equals(myBoard[currentPos[0]]
                            [currentPos[1] - 1], Type.getSANDNAME())) {

                currentPos[0]--;
            } else if (currentPos[1] < myEndPos[1] &&
                    Objects.equals(myBoard[currentPos[0]]
                            [currentPos[1] - 1], Type.getSANDNAME())) {

                currentPos[1]++;
            } else if (currentPos[1] > myEndPos[1] &&
                    Objects.equals(myBoard[currentPos[0]]
                            [currentPos[1] - 1], Type.getSANDNAME())) {

                currentPos[1]--;
            } else {
                if (!pathTaken.isEmpty()) {
                    pathTaken.remove(pathTaken.size() - 1);
                    Arrays.copyOf(pathTaken.get(pathTaken.size() - 1), pathTaken.get(pathTaken.size() - 1).length);
                }
                continue;
            }

            pathTaken.add(Arrays.copyOf(currentPos, currentPos.length));

        }

    }









}
