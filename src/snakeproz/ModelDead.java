package snakeproz;
import java.util.LinkedList;
/**
 *
 * @author Kajkacz
 * Class inheriting from Model class for the Strategy design pattern, implementing
 * behaviour for the dead snake
 */
    public class ModelDead extends Model{
        /**
        * Function for moving snake - no movement after dying
        */
        @Override 
        public void moveSnake(){
        
        }
        /**
        * Returning label to be displayed - in this case "Game Over" and final score
        */
        @Override
        public String getLabel(){
            return "GAME OVER!Score is " + score;
        }
        /**
        * Constructor copying state from provided model
        * @param  model to be copied, except for fruit
        * Direction set to Stop
        */
        public ModelDead(Model model){
        super();
        this.snakeSegments = new LinkedList<SnakeElement>(model.getSegments());
        direction = AppConstants.Direction.STOP;
        this.fruit = null;
        this.score = model.score;
        }
    
    }