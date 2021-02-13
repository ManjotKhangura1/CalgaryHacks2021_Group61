import java.util.logging.Handler;

public class Player{

    private int temperature;
    private int shovelLevel;
    private int walkSpeed;
    private int throwDistance;
    private int maxSnowCap = 1;
    private int playerOrientation;
    private int playerX;
    private int playerY;
    private Handler charHandler;

    Player(int X, int Y, int orientation, Handler handler){
        this.playerX = X;
        this.playerY = Y;
        this.playerOrientation = orientation;
        this.charHandler = handler;
    }

    public void playerMove(int xOffset, int yOffset){

    }

    public void playerThrow(){

    }

    public void tick(){

    }

    public int getTemperature() {return temperature;}

    public int getShovelLevel() {return shovelLevel;}

    public int getWalkSpeed() {return walkSpeed;}

    public int getThrowDistance() {return throwDistance;}

    public int getMaxSnowCap() {return maxSnowCap;}

    public int getPlayerOrientation() {return playerOrientation;}

    public int getPlayerX() {return playerX;}

    public int getPlayerY() {return playerY;}

    public Handler getCharHandler() {return charHandler;}
}