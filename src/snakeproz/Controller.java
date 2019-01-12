/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeproz;

import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;


/**
 *
 * @author Kajkacz
 */
public class Controller  {
   Model model;
   View view;
   public Controller(Model m,View v){
       model = m;
       view = v;
       view.root.getScene().setOnKeyPressed(new EventHandler<KeyEvent>(){
           @Override
           public void handle(KeyEvent event){
               switch(event.getCode()){
                   case W: 
                   case UP:
                        if(model.direction!=AppConstants.Direction.DOWN)
                            model.direction = AppConstants.Direction.UP;
                        break;
                   case S:
                   case DOWN:
                        if(model.direction!=AppConstants.Direction.UP)
                            model.direction = AppConstants.Direction.DOWN;                      
                        break;
                   case A:
                   case LEFT:                       
                        if(model.direction!=AppConstants.Direction.RIGHT)
                            model.direction = AppConstants.Direction.LEFT;
                        break;
                   case D:
                   case RIGHT:
                         if(model.direction!=AppConstants.Direction.LEFT)
                            model.direction = AppConstants.Direction.RIGHT;
                        break;
                   default:
                       System.out.println("Key unrecognized");
               }
           } 
       
       });
   }
   
}
