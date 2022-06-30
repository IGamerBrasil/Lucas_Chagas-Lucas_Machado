package poo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.List;
import java.util.LinkedList;

//Lucas Chagas 21101046 - Lucas Machado 21104296
/**
 * Handles the game lifecycle and behavior
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Game {
    private static Game game = null;
    private Canhao canhao;
    private List<Character> activeChars;
    private boolean stFinished = false;
    private int score = 0;
    private int pointsPerKill;
    private int enemy = 0;
    
    private Game(){
    }
    
    public static Game getInstance(){
        if (game == null){
            game = new Game();
        }
        return(game);
    }

    public void addChar(Character c){
        activeChars.add(c);
        c.start();
    }
    
    public void eliminate(Character c){
        activeChars.remove(c);
        if( c instanceof Ball || c instanceof Slow || c instanceof Above){
            if(enemy > 0){
                enemy--;
            }
        }
    }   

    public void setPontosKill(int s){
        pointsPerKill = s;
    }

    public void score(){
         score+=pointsPerKill;
    }

    public boolean finished(){
        if(enemy == 0){
            stFinished = true;
        }
        return stFinished;
    }

    public void Start() {
        // Repositório de personagens
        activeChars = new LinkedList<>();
        
        // Adiciona o canhao
        canhao = new Canhao(400,550);
        
        activeChars.add(canhao);
        
        for(int i=0; i<5; i++){
           activeChars.add(new Ball(100+Params.getInstance().nextInt(Params.WINDOW_WIDTH - 50),Params.WINDOW_HEIGHT-(60*i)));
           activeChars.add(new Slow(100+Params.getInstance().nextInt(Params.WINDOW_WIDTH - 80),Params.WINDOW_HEIGHT-(100*i)));
           activeChars.add(new Above(Params.WINDOW_WIDTH -(70*i), Params.getInstance().nextInt(Params.WINDOW_HEIGHT - 60)));
           activeChars.add(new AboveSlow(Params.WINDOW_WIDTH -(46*i), Params.getInstance().nextInt(Params.WINDOW_HEIGHT - 40)));
           enemy+=4;
        }
        
        for(Character c:activeChars){
            c.start();
        }
 
    }

    
    public void Update(long currentTime, long deltaTime) {
        for(int i=0;i<activeChars.size();i++){
            Character este = activeChars.get(i);
            este.Update();
            for(int j =0; j<activeChars.size();j++){
                Character outro = activeChars.get(j);
                if ( este != outro){
                    este.testaColisao(outro);
                }
            }
        }
    }
    
    public void OnInput(KeyCode keyCode, boolean isPressed) {
        canhao.OnInput(keyCode, isPressed);
    }
    
    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setFont(Font.font(20));
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText("Score: " + score, 60, 20);

        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setFont(Font.font(20));
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText("Lifes: " + Canhao.getLifes(), 200, 20);

        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setFont(Font.font(20));
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText("Remain Shots: " + Canhao.getRemainShots(), 400, 20);

        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setFont(Font.font(20));
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText("Enemies: " + enemy, 600, 20);

        for(Character c:activeChars){
            c.Draw(graphicsContext);
        }

    }
}
