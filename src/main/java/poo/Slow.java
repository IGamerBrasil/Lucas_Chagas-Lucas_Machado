package poo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

//Lucas Chagas 21101046 - Lucas Machado 21104296

public class Slow extends Ball{

    private int spd = 1;

    public Slow(int px, int py) {
        super(px, py);
        
    }

    @Override
    public void start() {
        setDirH(1);
    }

    @Override
    public int getSpeed() {
        return spd;
    }

    @Override
    public void Update(){
        if (jaColidiu()){
            deactivate();
        }else{
            setPosX(getX() + getDirH() * getSpeed());
            // Se chegou no lado direito da tela ...
            if (getX() >= getLMaxH()){
                // Reposiciona no lado esquerdo e ...
                setPosX(getLMinH());
                // Sorteia o passo de avanço [1,5]
                setSpeed(Params.getInstance().nextInt(2)+1);
            }
        }
    }

    private  Image img = new Image(getClass().getResourceAsStream("/imagens/bigenemy.png"));

    public void Draw(GraphicsContext graphicsContext){
       graphicsContext.drawImage(img,getX(),getY(),48,48);  
    }
}
