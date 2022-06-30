package poo;

import javafx.scene.canvas.GraphicsContext;

//Lucas Chagas 21101046 Lucas Machado 21104296
/**
 * Represents the basic game character
 * @author Bernardo Copstein and Rafael Copstein
 */
public abstract class BasicElement implements Character{
    private int posX, posY;
    private int largura, altura;
    private int lminH,lmaxH;
    private int lminV,lmaxV;
    private int speed;
    private boolean active;
    private boolean colidiu;
    private int direction_horizontal, direction_vertical;
    
    public BasicElement(int startX,int startY){
        posX = startX;
        posY = startY;
        largura = 32;
        altura = 32;
        direction_horizontal = 0;
        direction_vertical = 0;
        active = true;
        colidiu = false;
        speed = 5;
        //tamanho boneco
        lminH = (int)(Params.WINDOW_WIDTH * 0.1);
        lmaxH = (int)(Params.WINDOW_WIDTH * 0.9);
        lminV = (int)(Params.WINDOW_HEIGHT * 0.1);
        lmaxV = (int)(Params.WINDOW_HEIGHT * 0.8);
    }
    
    @Override
    public int getX(){
        return(posX);
    }
    
    @Override
    public int getY(){
        return(posY);
    }
    
    @Override
    public int getAltura(){
        return(altura);
    }
    
    @Override
    public int getLargura(){
        return(largura);
    }

    @Override
    //se colidiu em outro boneco
    public void testaColisao(Character outro){
        if (colidiu){
            return;
        }
        // Monta a localizacao do boneco 1
        int p1x = this.getX();
        int p1y = this.getY();
        int p2x = p1x+this.getLargura();
        int p2y = p1y+this.getAltura();
        
        //Monta a localizacao do boneco 2
        int op1x = outro.getX();
        int op1y = outro.getY();
        int op2x = op1x+outro.getLargura();
        int op2y = op1y+outro.getAltura();
        
        // Verifica colisão
        if ( ((p1x <= op1x && p2x >= op1x) && (p1y <= op1y && p2y >= op1y)) ||
             ((p1x <= op2x && p2x >= op2x) && (p1y <= op2y && p2y >= op2y)) ){
            colidiu = true;
            //outro.setColidiu();
            if(this instanceof Shot){
                if(outro instanceof Ball){
                    Game.getInstance().setPontosKill(1);
                    Game.getInstance().score();
                }
                if(outro instanceof Above){
                    Game.getInstance().setPontosKill(5);
                    Game.getInstance().score();
                }
                if(outro instanceof AboveSlow){
                    Game.getInstance().setPontosKill(3);
                    Game.getInstance().score();
                }
                if(outro instanceof Slow){
                    Game.getInstance().setPontosKill(2);
                    Game.getInstance().score();
                }
            }
            
             if(this instanceof Canhao){
                 Canhao c = (Canhao) this;
                 c.charHealth();
             }
         
        }
    }
    
    //mostra para onde vai na horizontal
    public int getDirH(){
        return(direction_horizontal);
    }
    
    //mostra para onde vai na vertical
    public int getDirV(){
        return(direction_vertical);
    }
    
    public int getLMinH(){
        return(lminH);
    }

    public int getLMaxH(){
        return(lmaxH);
    }
    
    public int getLMinV(){
        return(lminV);
    }

    public int getLMaxV(){
        return(lmaxV);
    }
    
    public int getSpeed(){
        return(speed);
    }
    
    public void setPosX(int p){
        posX = p;
    }

    public void setPosY(int p){
        posY = p;
    }
    
    public void setLargAlt(int l,int a){
        largura = l;
        altura = a;
    }
    
    //para onde vai horizontalmente
    public void setDirH(int dirH){
        direction_horizontal = dirH;
    }

    //para onde vai verticalmente
    public void setDirV(int dirV){
        direction_vertical = dirV;
    }

    //tamanho minimo e maximo na horizontal do boneco
    public void setLimH(int min,int max){
        lminH = min;
        lmaxH = max;
    }

    //tamanho minimo e maximo na vertical do boneco
    public void setLimV(int min,int max){
        lminV = min;
        lmaxV = max;
    }

    public void setSpeed(int s){
        speed = s;
    }
        
    public void deactivate(){
        active = false;
        Game.getInstance().eliminate(this);
    }
    
    @Override
    public boolean jaColidiu(){
        return(colidiu);
    }
    
    @Override
    public void setColidiu(){
        colidiu = true;
    }
    
    @Override
    public  boolean isActive(){
        return(active);
    }
    
    @Override
    public abstract void start();    
        
    @Override
    public abstract void Update();
        
    @Override
    public abstract void Draw(GraphicsContext graphicsContext);
}
