import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends GameObject{

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

    public void playerMove(Direction direction){
        if (direction == Direction.Left) {
            this.playerX -= 1;
        } else if (direction == Direction.Right) {
            this.playerX += 1;
        } else if (direction == Direction.Down) {
            this.playerY += 1;
        } else if (direction == Direction.Up) {
            this.playerY -= 1;
        }
        setRotation(direction);
    }

    public void setRotation(Direction direction) {
        this.playerOrientation = direction;
    }

    public void playerThrow() {

    }

    public void display(Display d) {
        for (int x = playerX - 1; x <= playerX + 1; x++) {
            for (int y = playerY - 1; y <= playerY + 1; y++) {
                Main.board.drawTile(x,y);
            }
        }
        d.redTile(playerX, playerY);
    }

    public void tick() {
        if (Handler.kl.justPressed(KeyCode.W)) {
            playerMove(Direction.Up);
        } else if (Handler.kl.justPressed(KeyCode.A)) {
            playerMove(Direction.Left);
        } else if (Handler.kl.justPressed(KeyCode.S)) {
            playerMove(Direction.Down);
        } else if (Handler.kl.justPressed(KeyCode.D)) {
            playerMove(Direction.Right);
        }
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
