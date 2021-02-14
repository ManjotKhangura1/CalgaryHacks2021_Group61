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
    private int iStepX = 0;
    private int iStepY = 0;
    private Handler playerHandler;

    Player(int X, int Y, Direction orientation, Handler handler){
        this.playerX = X;
        this.playerY = Y;
        this.playerOrientation = orientation;
        this.playerHandler = handler;
    }

    public void playerMove(Direction direction) {
        int xOffset = 0;
        int yOffset = 0;
        if (direction == Direction.Left) {
            xOffset = -1;
        } else if (direction == Direction.Right) {
            xOffset = 1;
        } else if (direction == Direction.Down) {
            yOffset = 1;
        } else if (direction == Direction.Up) {
            yOffset = -1;
        }
        if (Main.board.getTile(playerX + xOffset, playerY + yOffset).getTileID() != TileID.Driveway) {
            return;
        }
        setRotation(direction);
        playerY += yOffset;
        playerX += xOffset;
        iStepX = (-xOffset) * playerHandler.display.pixelScale;
        iStepY = (-yOffset) * playerHandler.display.pixelScale;
    }

    public void setRotation(Direction direction) {
        this.playerOrientation = direction;
    }

    public void playerThrow() {

    }

    public void display(Display d) {
        if (iStepY != 0 || iStepX != 0) {
            return;
        }
        d.redTile(playerX,playerY,0,0);
    }

    public void tick() {
        int stepSize = 6;
        if (iStepX != 0) {
            if (iStepX > 0) {
                iStepX -= stepSize;
                Main.board.drawTile(playerX+1,playerY);
            } else {
                iStepX += stepSize;
                Main.board.drawTile(playerX-1,playerY);
            }

            playerHandler.display.redTile(playerX, playerY, iStepX, iStepY);
            return;
        } else if (iStepY != 0) {
            if (iStepY > 0) {
                iStepY -= stepSize;
                Main.board.drawTile(playerX,playerY+1);
            } else {
                iStepY += stepSize;
                Main.board.drawTile(playerX,playerY-1);
            }
            playerHandler.display.redTile(playerX, playerY, iStepX, iStepY);
            return;
        }
        if (Handler.kl.isPressed(KeyCode.W)) {
            playerMove(Direction.Up);
        } else if (Handler.kl.isPressed(KeyCode.A)) {
            playerMove(Direction.Left);
        } else if (Handler.kl.isPressed(KeyCode.S)) {
            playerMove(Direction.Down);
        } else if (Handler.kl.isPressed(KeyCode.D)) {
            playerMove(Direction.Right);
        }

        /*int index;
        if(xOffset == -1){
            index = animCounter % playerSpriteLeft.length;
            String prefix = "/images/PlayerSpriteLeft/";
            String postfix = ".png";
            playerSprite = prefix+playerSpriteLeft[index]+postfix;
        }
        else if(xOffset == 1){
            index = animCounter % playerSpriteRight.length;
            String prefix = "/images/PlayerSpriteRight/";
            String postfix = ".png";
            playerSprite = prefix+playerSpriteRight[index]+postfix;
        }
        else if(yOffset == -1){
            index = animCounter % playerSpriteBack.length;
            String prefix = "/images/PlayerSpriteBack/";
            String postfix = ".png";
            playerSprite = prefix+playerSpriteBack[index]+postfix;
        }
        else if(yOffset == 1){
            index = animCounter % playerSpriteFront.length;
            String prefix = "/images/PlayerSpriteFront/";
            String postfix = ".png";
            playerSprite = prefix+playerSpriteFront[index]+postfix;
        }
        else{
            if(getPlayerOrientation() == Direction.Up){
                index = animCounter % playerSpriteBackIdle.length;
                String prefix = "/images/PlayerSpriteBackIdle/";
                String postfix = ".png";
                playerSprite = prefix+playerSpriteBackIdle[index]+postfix;
            }
            else{
                index = animCounter % playerSpriteFrontIdle.length;
                String prefix = "/images/PlayerSpriteFrontIdle/";
                String postfix = ".png";
                playerSprite = prefix+playerSpriteFrontIdle[index]+postfix;
            }
        }*/
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
