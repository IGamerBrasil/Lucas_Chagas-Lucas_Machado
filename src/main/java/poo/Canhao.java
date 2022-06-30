package poo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;


//Lucas Chagas 21101046 Lucas Machado 21104296
/**
 * Represents the game Gun
 * @author Bernardo Copstein, Rafael Copstein
 */
public class Canhao extends BasicElement implements KeyboardCtrl{

    private static int life = 10;
    private static int shots = 0;
    private static int maxShots = 20;

    public Canhao(int px,int py){
        super(px,py);
    }    
    
    @Override
    public void start() {
        setLimH(20,Params.WINDOW_WIDTH-20);
        setLimV(Params.WINDOW_HEIGHT-100,Params.WINDOW_HEIGHT);
    }
    
    public void charHealth(){
        if(life > 0){
            life -= 2;
        }
    }

    public static int getLifes(){
        return life;
    }

    public static int getRemainShots(){
        return maxShots - shots; 
    }

    @Override
    public void Update() {
        setPosX(getX() + getDirH() * getSpeed());            
    }
    
    @Override
    public void OnInput(KeyCode keyCode, boolean isPressed) {
        if (keyCode == KeyCode.A){
            int dh = isPressed ? -1 : 0;
            setDirH(dh);
        }
        if (keyCode == KeyCode.D){
            int dh = isPressed ? 1 : 0;
            setDirH(dh);
        }
        if (keyCode == KeyCode.SPACE){ 
            if(shots < maxShots){
                Game.getInstance().addChar(new Shot(getX()+16,getY()-32));
                shots++;
            }
        }
        //if (keyCode == KeyCode.UP) do nothing
        //if (keyCode == KeyCode.DOWN) do nothing
    }
    
    @Override
    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Paint.valueOf("#FF0000"));
        graphicsContext.fillRect(getX(), getY()+16, 32, 32);
        graphicsContext.fillRect(getX()+8, getY()-16, 16, 48);        
    }   
}
