package poo;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

//Link Repositorio Git https://github.com/IGamerBrasil/SpaceInvaders_2022;

//Lucas Chagas 21101046 - Lucas Machado 21104296
/**
 * Handles window initialization and primary game setup
 * @author Bernardo Copstein, Rafael Copstein
 */

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Initialize Window
        stage.setTitle(Params.WINDOW_TITLEMENU);
 
        Group root2 = new Group();

        Button btn = new Button();
        btn.setText("Start");

        Scene splash = new Scene(root2, 300, 250);
        root2.getChildren().add(btn);
       

        stage.setScene(splash);

        Group root = new Group();

        //Group root3 = new Group();

        Scene scene = new Scene( root );

        //Scene scene2 = new Scene( root3 );

        btn.setOnAction(e -> {
            stage.setTitle(Params.WINDOW_TITLE);
            stage.setScene( scene );
        }); 
        
        Canvas canvas = new Canvas(Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT );
        root.getChildren().add( canvas );

        // Setup Game object
        Game.getInstance().Start();
        
        // Register User Input Handler
        scene.setOnKeyPressed((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), true);
        });
       
        scene.setOnKeyReleased((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), false);
        });

        if( Game.getInstance().finished() == true){
            stage.setTitle(Params.WINDOW_TITLE);
            stage.setScene(scene);
            Game.getInstance().Start();
        }

        
        // Register Game Loop       
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        new AnimationTimer()
        {
            long lastNanoTime = System.nanoTime();
            
            @Override
            public void handle(long currentNanoTime)
            {
                long deltaTime = currentNanoTime - lastNanoTime;
                
                Game.getInstance().Update(currentNanoTime, deltaTime);
                gc.clearRect(0, 0, Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
                Game.getInstance().Draw(gc);
                
                lastNanoTime = currentNanoTime;
            }
            
        }.start();

        // Show window
        stage.show();
    }
    
    public static void main(String args[]) {
        launch();
    }
}
