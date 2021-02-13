import java.util.logging.Handler;

public class Player{

    private int temperature;
    private int shovelLevel;
    private int walkSpeed;
    private int throwDistance;
    private int maxSnowCap = 1;
    private Direction playerOrientation;
    private int playerX;
    private int playerY;
    private Handler playerHandler;

    Player(int X, int Y, Direction orientation, Handler handler){
        this.playerX = X;
        this.playerY = Y;
        this.playerOrientation = orientation;
        this.playerHandler = handler;
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

    public Direction getPlayerOrientation() {return playerOrientation;}

    public int getPlayerX() {return playerX;}

    public int getPlayerY() {return playerY;}

    public Handler getPlayerHandler() {return playerHandler;}
}
