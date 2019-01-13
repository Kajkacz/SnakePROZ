package snakeproz;

import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle ;
import javafx.scene.shape.Circle ;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import snakeproz.Model.SnakeElement;
/**
 *
 * @author Kajkacz
 * Main class implementing JavaFx Application
    @variable model - the model governing the move of snake
    @variable root - main pane of app
    @variable controller - controller with keyboard listener
    @variable stage - stage object
    @variable currentSnake - the List of rectangles representing snake
    @variable fruitImage - the cricle representing fruit
    @variable offset - offset of position of board vs. pane
    @variable label - label on top of the screen
*/
public class View extends Application {
    Model model;
    Pane root;
    Controller controller;
    Stage stage;
    LinkedList<Rectangle> currentSnake;
    Circle fruitImage;
    int offset;
    Label label;
    /**
     * 
     * @param primaryStage - ovveriding function from JavaFX app
     * Setting the start conditions, constructing all objects, starting the game loop
    */
    @Override
    public void start(Stage primaryStage) {
        model = new Model(AppConstants.boardSize);
        root = new Pane();
        currentSnake = new LinkedList<Rectangle>();
        Scene scene = new Scene(root, AppConstants.sceneSize, AppConstants.sceneSize);
        controller = new Controller(model,this);
        stage = primaryStage;
        offset = (AppConstants.fieldOffset)*AppConstants.fieldSize;
        primaryStage.setTitle("Snek");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Rectangle board = new Rectangle(offset - AppConstants.fieldSize,offset - AppConstants.fieldSize,
                (AppConstants.boardSize+2)*AppConstants.fieldSize,
                (AppConstants.boardSize+2)*AppConstants.fieldSize);        
        root.getChildren().add(board);
        Timer timer = new Timer();
        timer.schedule(new ProgressSnake(), 0, AppConstants.gameSpeed);
        
     
        }
    
    /*
    * Fuction for refreshing the visual part of the app
    */
    public void refresh(){
        if(model.dead)
        {
            model = new ModelDead(model);
        }

        LinkedList<SnakeElement> snake = model.getSegments();
        root.getChildren().removeAll(currentSnake);

        root.getChildren().remove(label);
        label = new Label(model.getLabel());
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(new Font("Arial", 30));
        label.setContentDisplay(ContentDisplay.CENTER);
        label.setMaxWidth(Double.POSITIVE_INFINITY);
        label.setMaxHeight(Double.POSITIVE_INFINITY);
        label.setStyle("-fx-border-color: blue;");

        root.getChildren().add(label);

        currentSnake.removeAll(currentSnake);

        for(SnakeElement element: snake){
//                System.out.println(element.x*AppConstants.fieldSize + " " +  element.y*AppConstants.fieldSize);
           // System.out.println("Snake Size:" + snake.size());
            Rectangle rec = new Rectangle(element.x*AppConstants.fieldSize + offset,element.y*AppConstants.fieldSize + offset,AppConstants.fieldSize,AppConstants.fieldSize);
            rec.setFill(Color.RED);
            currentSnake.add(rec);
            root.getChildren().add(rec);

//                System.out.println("In the list : " + element.x  + " " +  element.y);

        }
        root.getChildren().remove(fruitImage);
        if(model.fruit!=null)
        {   fruitImage = new Circle(model.fruit.x*AppConstants.fieldSize + AppConstants.fieldSize/2 + offset,model.fruit.y*AppConstants.fieldSize + AppConstants.fieldSize/2 + offset,0.3*AppConstants.fieldSize);
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
    /*
    * Setting the Timer for the main loop of the game
    */ 
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
