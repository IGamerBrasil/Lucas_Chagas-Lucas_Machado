package poo;

import java.util.Random;

//Lucas Chagas 21101046 - Lucas Machado 21104296

public class Params{
    public static final String WINDOW_TITLEMENU = "My GameMenu V1.0";
    public static final String WINDOW_TITLE = "My Game V1.0";
    public static final String WINDOW_TITLE2 = "My Game Phase 2 V1.0";
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    
    private static Params params = null;
    private Random rnd;
    
    private Params(){
        rnd = new Random();
    }
    
    public static Params getInstance(){
        if (params == null){
            params = new Params();
        }
        return(params);
    }
    
    public int nextInt(int lim){
        return(rnd.nextInt(lim));
    }
}

