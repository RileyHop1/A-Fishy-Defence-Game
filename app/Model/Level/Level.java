package Model.Level;

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
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    /**Noise for terrain generation.*/
    private static final int NOISE = 10;
    /**This is the overall path the hooks will take.*/
    private static final ArrayList<int[]> COMPLETE_PATH = new ArrayList<>();
    /**This is the icon used for paths.*/
    private static final String PATH_ICON = "P";
    /**This is the icon used for terrain that can be placed on.*/
    private static final String SAND_ICON = "-";
    /**This is terrain that blocks player placement.*/
    private static final String WALL_ICON = "#";



    /**
     * This sets a map size then applies it to the board.
     * @param theMapSize This is the size of the map you want.
     */
    public static void chooseMapSize(final int theMapSize) {

        myMapSize = theMapSize;
        myBoard = new String[myMapSize][myMapSize];
    }

    /**
     * This generates a new board to player on.
     */
    public static void generateBoard() {

        System.out.println("Generate board");
        chooseMapSize(20);



        createANewRandomPath();
        placeObstacles();

        initialBoard();
        printBoard();
        //printMyCompletePath();



    }

    /**
     * This chooses a random start position.
     */
    private static void setRandomStart() {
        if (myMapSize < 10) {
            throw new RuntimeException("Map size must be greater " +
                    "than or equal to 10");
        }

        myStartPos[1] = RANDOM.nextInt(myMapSize);

        //This makes sure the start is always on the border
        if (myStartPos[1] == 0 || myStartPos[1]
                == myMapSize - 1 ) {
            myStartPos[0] = RANDOM.nextInt(myMapSize);
        } else {
            if(RANDOM.nextBoolean()) {
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

        myEndPos[0] = myMapSize - 1 - myStartPos[0];
        myEndPos[1] = myMapSize - 1 - myStartPos[1];
    }

    /**
     * Creates a new, random path for the hooks, with
     * the advance pivot based system, worldclass.
     */
    private static void createANewRandomPath() {

        //Creates a random start and end point for the maze
        setRandomStart();
        setRandomEnd();

        /*
        * Pivot points are random spots on the map that the
        * path generation algorithm will go to, this adds some controlled randomness
        * to the shape of the path.*/
        int pivotPoints = RANDOM.nextInt(NOISE);

        //Grabs the entrance of the hooks.
        int[] startPoint = Arrays.copyOf(myStartPos, myStartPos.length);

        for (int i = 0; i < pivotPoints; i++) {

            int[] endPoint = {RANDOM.nextInt(myMapSize), RANDOM.nextInt(myMapSize)};

            //Make sure the pivot point isn't already a start point or end point
            while (endPoint[0] == startPoint[0] &&  endPoint[1] == startPoint[1]
                    || endPoint[0] == myEndPos[0] &&  endPoint[1] == myEndPos[1]
                    || myBoard[endPoint[0]][endPoint[1]] != null) {
                endPoint = new int[]{RANDOM.nextInt(myMapSize), RANDOM.nextInt(myMapSize)};

            }
            System.out.println("Generating board.");
            generatePath(startPoint,endPoint);
            startPoint = endPoint;


        }
        generatePath(startPoint,myEndPos);
    }

    /**
     * This is a helper method for generating a path.
     *
     * @param theStartingPos This is the starting position as [x, y].
     * @param theEndPos      This is the ending position as [x, y ].
     */
    private static void generatePath(final int[] theStartingPos, final int[] theEndPos) {

        if (theStartingPos.length != 2) {
            throw new RuntimeException("The starting pos must " +
                    "be an array of length 2");
        }
        if (theEndPos.length != 2) {
            throw new RuntimeException("The ending pos must " +
                    "be an array of length 2");
        }

        //This will hold the previous steps taken in case of back tracking.
        ArrayList<int[]> pathTaken = new ArrayList<>();

        //grabs the current position.
        int[] currentPos = Arrays.copyOf(theStartingPos,theStartingPos.length);


        int steps = 0;
        //Hard caps the amount of loops.
        int maxSteps = (int) Math.pow(myMapSize, 2);

        System.out.println(myStartPos[0] + ", " + myStartPos[1]);
        System.out.println(theEndPos[0] + ", " + theEndPos[1]);

        while (currentPos[0] != theEndPos[0]
                || currentPos[1] != theEndPos[1]) {

            //Safeguard against infinite loops.
            if (steps > maxSteps) {
                return;
            }

            //increases the steps taken.
            steps++;

            //This is the typical greedy version of pathfinding.
            if (currentPos[0] < theEndPos[0]
                    && (myBoard[currentPos[0] + 1][currentPos[1]] == null
                    || Objects.equals(myBoard[currentPos[0] + 1][currentPos[1]], PATH_ICON))) {
                currentPos[0]++;
            } else if (currentPos[0] > theEndPos[0]
                    && (myBoard[currentPos[0] - 1][currentPos[1]] == null
                    || Objects.equals(myBoard[currentPos[0] - 1][currentPos[1]], PATH_ICON))) {
                currentPos[0]--;
            } else if (currentPos[1] < theEndPos[1]
                    && (myBoard[currentPos[0]][currentPos[1] + 1] == null
                    || Objects.equals(myBoard[currentPos[0]][currentPos[1] + 1], PATH_ICON))) {
                currentPos[1]++;
            } else if (currentPos[1] > theEndPos[1]
                    && (myBoard[currentPos[0]][currentPos[1] - 1] == null
                    || Objects.equals(myBoard[currentPos[0]][currentPos[1] - 1], PATH_ICON))) {
                currentPos[1]--;
            } else {

                if (!pathTaken.isEmpty()) pathTaken.removeLast();

                if (!pathTaken.isEmpty()) {

                    int[] lastPos = pathTaken.getLast();
                    currentPos = Arrays.copyOf(lastPos, lastPos.length);

                } else {
                    currentPos = Arrays.copyOf(theStartingPos,theStartingPos.length);
                }
                continue;
            }

            pathTaken.add(Arrays.copyOf(currentPos, currentPos.length));


        }
        applyPath(pathTaken);
        COMPLETE_PATH.addAll(pathTaken);

    }


    /**
     * This is a helper method that, applies the path to the board.
     * @param thePath The path taken to the end point.
     */
    private static void applyPath(final ArrayList<int[]> thePath) {

        //instantly break if the path is empty.
        if (thePath.isEmpty()) {
            return;
        }

        for (int[] coord : thePath) {
            if (myBoard[coord[0]][coord[1]] == null) {
                myBoard[coord[0]][coord[1]] = PATH_ICON;
            }
        }
        myBoard[myStartPos[0]][myStartPos[1]] = PATH_ICON;
        myBoard[myEndPos[0]][myEndPos[1]] = PATH_ICON;
    }


    /**
     * This fills the board with sand tiles.
     */
    private static void initialBoard() {

        for (int i = 0; i < myBoard.length; i++) {
            for (int j = 0; j < myBoard[i].length; j++) {
                if (myBoard[i][j] == null) {
                    myBoard[i][j] = SAND_ICON;
                }
            }
        }
    }

    /**
     * This randomly places obstacles on the map in empty spots.
     */
    private static void placeObstacles() {
        //This picks random spots on the board to place rock clusters
        int clusterPoints = RANDOM.nextInt(NOISE);

        //This is the max size a cluster can get in any direction
        int depth = 5;




        for (int i = 0; i < clusterPoints; i++) {
            //Picks an initial center for the cluster
            int[] clusterCenter = {RANDOM.nextInt(myMapSize),RANDOM.nextInt(myMapSize)};

            //Make sure the wall isn't blocking a path and that a wall is not on
            //Another wall.
            while ((Objects.equals(myBoard[clusterCenter[0]][clusterCenter[1]], PATH_ICON))
                    || (Objects.equals(myBoard[clusterCenter[0]][clusterCenter[1]], WALL_ICON))) {
                clusterCenter = new int[]{RANDOM.nextInt(myMapSize), RANDOM.nextInt(myMapSize)};
            }
            System.out.println(" This is the depth: "+ depth);
            generateObstacle(clusterCenter, RANDOM.nextInt(depth) + 1);

        }
    }

    /**This method recursively generates walls, completely randomly.
     *
     * @param theClusterCoords The center of the generation.
     * @param theDepth This is the distance of generation.
     */
    private static void generateObstacle(final int[] theClusterCoords, final  int theDepth) {

        // Breaks the recursion if a path is found.
        if (myBoard[theClusterCoords[0]][theClusterCoords[1]] != null) return;

        //Create the rock before entering base case.
        myBoard[theClusterCoords[0]][theClusterCoords[1]] = WALL_ICON;

        //Base case to end recursion, must be at least 2 because a bound of 0 is illegal.
        if ( theDepth < 2) return;


        int depth = theDepth - 1;

        System.out.println(theDepth);

        //Expands out in each direction randomly
        if (theClusterCoords[0] + 1 < myMapSize
                && myBoard[theClusterCoords[0] + 1][theClusterCoords[1]] == null) {
            int[] newClusterCoords = Arrays.copyOf(theClusterCoords, theClusterCoords.length);
            newClusterCoords[0]++;
            generateObstacle(newClusterCoords, RANDOM.nextInt(depth));
        } if (theClusterCoords[1] + 1 < myMapSize
                && myBoard[theClusterCoords[0]][theClusterCoords[1] + 1] == null) {
            int[] newClusterCoords = Arrays.copyOf(theClusterCoords, theClusterCoords.length);
            newClusterCoords[1]++;
            generateObstacle(newClusterCoords, RANDOM.nextInt(depth));
        }  if (theClusterCoords[0] - 1 >= 0
                && myBoard[theClusterCoords[0] - 1][theClusterCoords[1]] == null) {
            int[] newClusterCoords = Arrays.copyOf(theClusterCoords, theClusterCoords.length);
            newClusterCoords[0]--;
            generateObstacle(newClusterCoords, RANDOM.nextInt(depth));
        } if (theClusterCoords[1] - 1 >= 0
                && myBoard[theClusterCoords[0]][theClusterCoords[1] - 1] == null) {
            int[] newClusterCoords = Arrays.copyOf(theClusterCoords, theClusterCoords.length);
            newClusterCoords[1]--;
            generateObstacle(newClusterCoords, RANDOM.nextInt(depth));
        }


    }

    /**
     * Prints the board for testing.
     */
    public static void printBoard() {
        System.out.println("the board");

        for (int i = 0; i < myBoard.length; i++) {
            for (int j = 0; j < myBoard[i].length; j++) {
                String cell = myBoard[i][j];
                if (cell == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(cell.charAt(0) + " ");
                }
            }
            System.out.println();
        }
    }


    /**
     * This prints the path the hooks will take.
     */
    public static void printMyCompletePath() {
        for (int i = 0; i < COMPLETE_PATH.size(); i++) {
            int[] pathSegment = COMPLETE_PATH.get(i);
            System.out.print("Segment " + i + ": ");
            for (int value : pathSegment) {
                System.out.print(value + " ");
            }
            System.out.println(); // Move to next line after each segment
        }
    }









}
