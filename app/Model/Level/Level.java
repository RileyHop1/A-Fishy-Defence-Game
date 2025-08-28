package Model.Level;

import Model.Entities.Type;

import java.util.ArrayList;
import java.util.Arrays;
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
        myBoard = new String[myMapSize][myMapSize];
        initialBoard();
        setRandomStart();
        setRandomEnd();
        generatePath(myEndPos, Type.CORAL);
        initialBoard();



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

    /**
     * This generates a random path from a given position.
     * @param theStartingPos This is the starting position as [x, y] of the randomly
     *                       Generated path, it will find a path from
     *                       this position to the end point.
     * @param theType This is the type of the path.
     * @return A truthy value on if the path was able to be generated.
     */
    private static boolean generatePath(final int[] theStartingPos, final Type theType) {

        if (theStartingPos.length != 2) {
            throw new RuntimeException("The starting pos must " +
                    "be an array of length 2");
        }

        //This will hold the previous steps taken in case of back tracking.
        ArrayList<int[]> pathTaken = new ArrayList<>();

        //grabs the current position.
        int[] currentPos = Arrays.copyOf(theStartingPos,theStartingPos.length);


        int steps = 0;
        //Hard caps the amount of loops.
        int maxSteps = (int) Math.pow(myMapSize, 2);



        while (currentPos[0] != myEndPos[0]
                || currentPos[1] != myEndPos[1]) {

            //Safeguard against infinite loops.
            if (steps > maxSteps) {
                return false;
            }

            //increases the steps taken.
            steps++;

            //This will randomly choose a direction as noise 1/5th and also check if its sand.
            if (myRand.nextInt(20) == 0 && currentPos[0] + 1 < myMapSize
                    && myBoard[currentPos[0] + 1][currentPos[1]] == null) {
                currentPos[0]++;
                pathTaken.add(Arrays.copyOf(currentPos, currentPos.length));
                continue;
            } else if (myRand.nextInt(20) == 0 && currentPos[1] + 1 < myMapSize
                    && myBoard[currentPos[0]][currentPos[1] + 1] == null) {
                currentPos[1]++;
                pathTaken.add(Arrays.copyOf(currentPos, currentPos.length));
                continue;
            } else if (myRand.nextInt(20) == 0 && currentPos[0] - 1 >= 0
                    && myBoard[currentPos[0] - 1][currentPos[1]] == null) {
                currentPos[0]--;
                pathTaken.add(Arrays.copyOf(currentPos, currentPos.length));
                continue;
            } else if (myRand.nextInt(20) == 0 && currentPos[1] - 1 >= 0
                    && myBoard[currentPos[0]][currentPos[1] - 1] == null) {
                currentPos[1]--;
                pathTaken.add(Arrays.copyOf(currentPos, currentPos.length));
                continue;
            }

            //This is the typical greedy version of pathfinding.
            if (currentPos[0] < myEndPos[0]
                    && myBoard[currentPos[0] + 1][currentPos[1]] == null) {
                currentPos[0]++;
            } else if (currentPos[0] > myEndPos[0]
                    && myBoard[currentPos[0] - 1][currentPos[1]] == null) {
                currentPos[0]--;
            } else if (currentPos[1] < myEndPos[1]
                    && myBoard[currentPos[0]][currentPos[1] + 1] == null) {
                currentPos[1]++;
            } else if (currentPos[1] > myEndPos[1]
                    && myBoard[currentPos[0]][currentPos[1] - 1] == null) {
                currentPos[1]--;
            } else {
                //This will stop retreads of bad paths.
                myBoard[currentPos[0]][currentPos[1]] = Type.getBadpath();
                pathTaken.remove(pathTaken.size() - 1);

                if (!pathTaken.isEmpty()) {

                    int[] lastPos = pathTaken.get(pathTaken.size() - 1);
                    currentPos = Arrays.copyOf(lastPos, lastPos.length);

                } else {
                    currentPos = Arrays.copyOf(theStartingPos,theStartingPos.length);
                }
                continue;
            }

            pathTaken.add(Arrays.copyOf(currentPos, currentPos.length));


        }
        applyPath(pathTaken, theType);
        return true;

    }


    /**
     * This applies the path take to the board.
     * @param thePath The path taken to the end point.
     * @param theType the type of the path.
     */
    private static void applyPath(final ArrayList<int[]> thePath, final Type theType) {

        //instantly break if the path is empty.
        if (thePath.isEmpty()) {
            return;
        }

        for (int[] coord : thePath) {
            if (myBoard[coord[0]][coord[1]] == Type.getSANDNAME()
                    || myBoard[coord[0]][coord[1]] == Type.getBadpath()) {
                myBoard[coord[0]][coord[1]] = switch (theType) {
                    case ICE -> Type.getICENAME();
                    case ROCK -> Type.getROCKNAME();
                    case FIRE -> Type.getFIRENAME();
                    case AIR -> Type.getAIRNAME();
                    default -> Type.getCORALNAME();
                };
            }
        }
    }


    /**
     * This fills the board with sand tiles.
     */
    private static void initialBoard() {

        for (int i = 0; i < myBoard.length; i++) {
            for (int j = 0; j < myBoard[i].length; j++) {
                if (myBoard[i][j] == null || myBoard[i][j] == Type.getBadpath()) {
                    myBoard[i][j] = Type.getSANDNAME();
                }
            }
        }
    }









}
