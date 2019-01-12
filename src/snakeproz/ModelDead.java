/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeproz;
import java.util.LinkedList;
/**
 *
 * @author Kajkacz
 */
    public class ModelDead extends Model{
        @Override 
        public void moveSnake(){
        
        }
        @Override
        public String getLabel(){
            return "GAME OVER!Score is " + score;
        }
        public ModelDead(Model model){
        super();
        this.snakeSegments = new LinkedList<SnakeElement>(model.getSegments());
        direction = AppConstants.Direction.STOP;
        this.fruit = null;
        this.score = model.score;
        }
    
    }