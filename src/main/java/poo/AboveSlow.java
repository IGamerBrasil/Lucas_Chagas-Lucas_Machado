package poo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

//Lucas Chagas 21101046 - Lucas Machado 21104296

public class AboveSlow extends Above{

    private int spd = 3;

    public AboveSlow(int px, int py) {
        super(px, py);
    }
    
    @Override
    public void start() {
        setDirV(1);
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
            setPosY(getY() + getDirV() * getSpeed());
            // Se chegou no lado direito da tela ...
            if (getY() >= getLMaxV()){
                // Reposiciona no lado esquerdo e ...
                setPosY(getLMinV());
                // Sorteia o passo de avanço [1,5]
                setSpeed(Params.getInstance().nextInt(3)+1);
            }
        }
    }

    private  Image img = new Image(getClass().getResourceAsStream("/imagens/alienL.png"));

    public void Draw(GraphicsContext graphicsContext){
       graphicsContext.drawImage(img,getX(),getY(),28,28);  
    }
}
