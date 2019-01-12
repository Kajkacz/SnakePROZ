/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeproz;

import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle ;
import javafx.scene.shape.Circle ;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.control.Label;
import snakeproz.Model.SnakeElement;
/**
 *
 * @author Kajkacz
 */
public class View extends Application {
    Model model;
    Pane root;
    Controller controller;
    Stage stage;
    LinkedList<Rectangle> currentSnake;
    Circle fruitImage;
    int[] offset;
    Label label;
    @Override
    public void start(Stage primaryStage) {
        model = new Model(AppConstants.boardSize);
        root = new Pane();
        currentSnake = new LinkedList<Rectangle>();
        Scene scene = new Scene(root, AppConstants.sceneWidth, AppConstants.sceneHeight);
        controller = new Controller(model,this);
        stage = primaryStage;
        offset = new int[2]; 
        offset[0] =  (AppConstants.sceneWidth - AppConstants.boardSize*AppConstants.fieldSize)/2;
        offset[1] =  (AppConstants.sceneHeight - AppConstants.boardSize*AppConstants.fieldSize)/2;
        
        primaryStage.setTitle("Snek");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Rectangle board = new Rectangle(AppConstants.sceneWidth/4 - AppConstants.fieldSize,AppConstants.sceneHeight/4 - AppConstants.fieldSize,(AppConstants.boardSize+2)*AppConstants.fieldSize,(AppConstants.boardSize+2)*AppConstants.fieldSize);        
        root.getChildren().add(board);
        Timer timer = new Timer();
        timer.schedule(new ProgressSnake(), 0, AppConstants.gameSpeed);
        
     
        }
        
        public void refresh(){
            if(model.dead)
            {
                model = new ModelDead(model);
            }
            
            LinkedList<SnakeElement> snake = model.getSegments();
            root.getChildren().removeAll(currentSnake);
            
            root.getChildren().remove(label);
            label = new Label(model.getLabel());
            root.getChildren().add(label);
            
            currentSnake.removeAll(currentSnake);

            for(SnakeElement element: snake){
//                System.out.println(element.x*AppConstants.fieldSize + " " +  element.y*AppConstants.fieldSize);
               // System.out.println("Snake Size:" + snake.size());
                Rectangle rec = new Rectangle(element.x*AppConstants.fieldSize + offset[0],element.y*AppConstants.fieldSize + offset[1],AppConstants.fieldSize,AppConstants.fieldSize);
                rec.setFill(Color.RED);
                currentSnake.add(rec);
                root.getChildren().add(rec);
            
//                System.out.println("In the list : " + element.x  + " " +  element.y);

            }
            root.getChildren().remove(fruitImage);
            if(model.fruit!=null)
            {   fruitImage = new Circle(model.fruit.x*AppConstants.fieldSize + AppConstants.fieldSize/2 + offset[0],model.fruit.y*AppConstants.fieldSize + AppConstants.fieldSize/2 + offset[1],0.3*AppConstants.fieldSize);
                fruitImage.setFill(Color.BLUE);
                root.getChildren().add(fruitImage);
            }
            stage.show();
            
        }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private class ProgressSnake extends TimerTask {
        public void run() {
            Platform.runLater(new Runnable(){
                public void run(){
                    model.moveSnake();
                    refresh();
                }
            });
        }
    }
}
