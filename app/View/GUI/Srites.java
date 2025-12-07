package View.GUI;

public enum Srites {



    PATH("Sprites/terrain/Sand_path_tile.png"),
    ROCK("Sprites/terrain/Sand_Rock_tiling.png"),
    SAND("Sprites/terrain/Sand_Tiling.png"),
    ICON("Shimp_icon.png");

    /**This is the icon used for paths.*/
    private static final String PATH_ICON = "P";
    /**This is the icon used for terrain that can be placed on.*/
    private static final  String SAND_ICON = "-";
    /**This is terrain that blocks player placement.*/
    private static final String WALL_ICON = "#";

    final String THE_PATH;

    Srites(String theFilePath) {
        this.THE_PATH = theFilePath;
    }

    public String getFilePath(final String theIcon) {

        String path;

        switch(theIcon) {

            case PATH_ICON -> path = PATH.THE_PATH;
            case WALL_ICON -> path = ROCK.THE_PATH;
            case SAND_ICON -> path = SAND.THE_PATH;
            default ->  path = ICON.THE_PATH;

        }
            return path;
    }

}
