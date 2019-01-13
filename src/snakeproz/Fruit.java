package snakeproz;
import java.util.Random;
/**
 *
 * @author Kajkacz
 * The class representing "fruits" that are eaten by Snake, implementing Singleton design pattern
 * @variable  x - position x of the fruit
 * @variable y - position y of the fruit
 * @variable fr - Fruit object for the purposes of singleton design pattern
 */
    public final class Fruit{
        int x;
        int y;
        private static volatile Fruit fr = null;
        /**
         * The function for singleton pattern calling the default constructor.
         * @return  Either new Fruit if none is found or the exisitng one referenced in class
         */
        public static Fruit getFruit(){
            if(fr ==null)
                fr = new Fruit();
            return fr;
        }
        
        /**
         * Function used for reseting position of the Fruit on board
         * @return a new Fruit object
         */
        public static Fruit newFruit(){
            fr = new Fruit();
            return fr;
        }
        /*
        * Constructor fot the class assigning random postion in the range of 0 to boardSize
        */
        private Fruit(){
            Random rand = new Random(System.currentTimeMillis());
            x = rand.nextInt(AppConstants.boardSize);
            y = rand.nextInt(AppConstants.boardSize);
        }
        
    }