package poo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

//Lucas Chagas 21101046 - Lucas Machado 21104296

public class Above extends Ball{

    private int spd = 6;

    public Above(int px, int py) {
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
        // if (jaColidiu()){
        //     deactivate();
        // }else{
        //     setPosX(getX() + getDirH() * getSpeed());
        //     // Se chegou no lado direito da tela ...
        //     if (getX() >= getLMinH()){
        //         // Reposiciona no lado esquerdo e ...
        //         setPosX(getLMaxH());
        //         // Sorteia o passo de avanço [1,5]
        //         setSpeed(Params.getInstance().nextInt()+1);
        //     }
        // }
        if (jaColidiu()){
            deactivate();
        }else{
            setPosY(getY() + getDirV() * getSpeed());
            // Se chegou no lado direito da tela ...
            if (getY() >= getLMaxV()){
                // Reposiciona no lado esquerdo e ...
                setPosY(getLMinV());
                // Sorteia o passo de avanço [1,5]
                setSpeed(Params.getInstance().nextInt(6)+1);
            }
        }
    }

    private  Image img = new Image(getClass().getResourceAsStream("/imagens/inv.png"));

    public void Draw(GraphicsContext graphicsContext){
       graphicsContext.drawImage(img,getX(),getY(),48,48);  
    }
    
}
