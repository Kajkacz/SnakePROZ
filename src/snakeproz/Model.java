package snakeproz;


import java.util.Random;
import java.util.LinkedList;

/**
 * Model of behaviour for snake
 * @author Kajkacz
 *  @variable snakeSegments - the list of exisitng snake segments
 *  @variable direction - current direction of movement
 *  @variable fruit - current fruit object
 *  @variable dead - is the snake dead?
 *  @variable score - the current score of the player
 *   
 */
public class Model{
    public LinkedList<SnakeElement> snakeSegments;
    public AppConstants.Direction direction; 
    public Fruit fruit ;
    public boolean dead = false;
    public int score;
    /**
    * @return returns current snake segments
    */
    public LinkedList<SnakeElement>  getSegments(){
        return snakeSegments;
    }
    /**
     * default constructor
     */
    public Model(){
        }    
    /**
     * Constructor for model
     * @param maxSize  - the size of board moddeled
     */
    public Model(int maxSize){
        snakeSegments = new LinkedList<SnakeElement>();
        snakeSegments.add(new SnakeElement(maxSize,true));
        direction = AppConstants.Direction.STOP;
        fruit = Fruit.getFruit();
       }
    /**
     * @return Current string for label on top with current score 
     */
    public String getLabel(){
        return "Current Score is " + new Integer(score).toString();
    }
    /**
     * Move the snake accordingly, check for death and add the element if approprieate
     */
    public void moveSnake(){
        checkForDeath();
        int previousX = 0,previousY = 0;
        int newX = 0,newY = 0;
        boolean add = false;
        for(SnakeElement element : snakeSegments){
            if(element.isFirst()){
                System.out.println("Head position X : " + element.x + " , Y : " + element.y);
                if( element.x == Fruit.getFruit().x && element.y == Fruit.getFruit().y )
                {
                   newFruit();
                   add = true; 
                }
                previousX  =  newX = element.x;
                previousY  =  newY = element.y;
                switch(direction){
                    case UP:
                        element.y--;
                        break;
                    case DOWN:
                        element.y++;
                        break;
                    case RIGHT:
                        element.x++;
                        break;
                    case LEFT:
                        element.x--;
                       break;
                    default:                    
                       System.out.println("On Pause");
                }
            }
            else{
                if(direction != AppConstants.Direction.STOP)
                {
                    int temp = element.x;
                    element.x = previousX;
                    previousX = temp;
                    temp = element.y;
                    element.y = previousY;
                    previousY = temp;
                }
                
            }
            
        }
        if(add)
        {
//            System.out.println("New X :" + newX + "New Y :" + newY );
            snakeSegments.add(new SnakeElement(newX,newY));
        }
    }
    /**
     * Check if snake collided with wall or itself
     */
    public void checkForDeath(){
        SnakeElement head = snakeSegments.get(0);
        for(SnakeElement element : snakeSegments)
        {
            if((!element.isFirst() && element.x == head.x && element.y == head.y)
                || (element.x < 1 && direction == AppConstants.Direction.LEFT)
                || (element.x > AppConstants.boardSize - 2 && direction == AppConstants.Direction.RIGHT)
                || (element.y < 1 && direction == AppConstants.Direction.UP)
                || (element.y> AppConstants.boardSize - 2  && direction == AppConstants.Direction.DOWN))
                dead = true;
        }
        
    }
    /**
     * Generate new Fruit and assign it to model
     */
    public void newFruit(){
        fruit = Fruit.newFruit();
        for(SnakeElement element : snakeSegments){
            if(element.x == fruit.x && element.y == fruit.y){
                newFruit();
            }
        }
        score++;
        System.out.println("Popped a new fruit at x:" + fruit.x + " and y :  " + fruit.y);
    }
    /**
     * Class for representing snake Elements
     * @variable x - x position of element
     * @variable y - y position of element
     * @variable isFirst - bool for checking if the element is first
     */
    public class SnakeElement{
        int x;
        int y;
        boolean isFirst = false;
        /**
         * @return returns the bool for if the element is first 
         */
        public boolean isFirst(){
            return isFirst;
        }
        /**
         * 
         * @param maxSize - the board size
         * @param first is the element first?
         */
        public SnakeElement(int maxSize, boolean first){
            this(maxSize);
            isFirst = first;
        }
        /**
        * @param  maxSize - the boardSize
        * assigns random position for first snake element
        */
        public SnakeElement(int maxSize){
            Random rand = new Random(System.currentTimeMillis());
            x = rand.nextInt(maxSize);
            y = rand.nextInt(maxSize);
        }
        /**
         * 
         * @param X - desired X of the element
         * @param Y - desired Y of the element 
         */
        public SnakeElement(int X,int Y){
            x = X;
            y = Y;
        }
    }

}
