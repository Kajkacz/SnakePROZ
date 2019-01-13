package snakeproz;

/**
 *
 * @author Kajkacz
 * All constants from the App
 */
public class AppConstants {
    // Possible move directions of Snake
    public enum Direction{
                            LEFT,RIGHT, UP,DOWN,STOP}

    // Field size in pixels
    public static final Integer fieldSize = 20;
    // Board Size in fields
    public static final Integer boardSize = 25;
    // Speed of game( the lower the faster
    public static final Integer gameSpeed = 100;
    // Size of border around the board
    public static final Integer fieldOffset = 4;
    // Calculated size of screen
    public static final Integer sceneSize= fieldSize*(boardSize+2*fieldOffset);
}
