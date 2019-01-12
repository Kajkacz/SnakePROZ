/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeproz;


import java.util.Random;
import java.util.LinkedList;

/**
 *
 * @author Kajkacz
 */
public class Model{
    public LinkedList<SnakeElement> snakeSegments;
    public AppConstants.Direction direction; 
    public Fruit fruit ;
    public boolean dead = false;
    public int score;
    
    public LinkedList<SnakeElement>  getSegments(){
        return snakeSegments;
    }
    public Model(){
        
       }    
    public Model(int maxSize){
        snakeSegments = new LinkedList<SnakeElement>();
        snakeSegments.add(new SnakeElement(maxSize,true));
        direction = AppConstants.Direction.STOP;
        fruit = Fruit.getFruit();
       }
    public String getLabel(){
        return "Current Score is " + new Integer(score).toString();
    }
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
    public class SnakeElement{
        int x;
        int y;
        boolean isFirst = false;
        
        public boolean isFirst(){
            return isFirst;
        }
        
        public SnakeElement(int maxSize, boolean first){
            this(maxSize);
            isFirst = first;
        }
        
        public SnakeElement(int maxSize){
            Random rand = new Random(System.currentTimeMillis());
            x = rand.nextInt(maxSize);
            y = rand.nextInt(maxSize);
        }
        public SnakeElement(int X,int Y){
            x = X;
            y = Y;
        }
    }

}
