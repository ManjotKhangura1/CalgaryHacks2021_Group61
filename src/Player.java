import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends GameObject{

    int[] playerSpriteBack = {1, 2};
    int[] playerSpriteBackIdle = {1, 2, 3, 4, 5, 6, 7, 8};
    int[] playerSpriteFront = {1, 2};
    int[] playerSpriteFrontIdle = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
    int[] playerSpriteLeft = {1, 2};
    int[] playerSpriteRight = {1, 2};

    private int temperature;
    private int shovelLevel;
    private int walkSpeed;
    private int throwDistance;
    private int maxSnowCap = 1;
    private Direction playerOrientation;
    private int playerX;
    private int playerY;
    private int xOffset = 0;
    private int yOffset = 0;
    private int iStepX = 0;
    private int iStepY = 0;
    private int animCounter = 0;
    private String playerSprite;
    private Handler playerHandler;
    private int frameTimer = 0;

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
        push(playerX + xOffset, playerY + yOffset, xOffset, yOffset);
        setRotation(direction);
        playerY += yOffset;
        playerX += xOffset;
        iStepX = (-xOffset) * playerHandler.display.pixelScale;
        iStepY = (-yOffset) * playerHandler.display.pixelScale;
    }

    public void push(int x, int y, int xOff, int yOff) {
        Tile t = Main.board.getTile(x + xOff,y + yOff);
        if (t.getTileID() == TileID.RoadTop || t.getTileID() == TileID.RoadBottom || t.getTileID() == TileID.House) {
            if (x < Main.board.width / 2) {
                push(x, y, -1, 0);
            } else {
                push(x, y, 1, 0);
            }
            return;
        }
        int snow = Main.board.getTile(x,y).getSnowLevel();
        Main.board.getTile(x,y).setSnowLevel(0);
        t.setSnowLevel(snow + t.getSnowLevel());
        Main.board.drawTile(x+xOff,y+yOff);
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
        Main.board.drawTile(playerX,playerY);
        Main.board.drawTile(playerX,playerY-1);
        d.playerTile(playerX,playerY,playerSprite, iStepX, iStepY);
    }

    public void tick() {
        int stepSize = 6;
        if (iStepX != 0) {
            if (iStepX > 0) {
                iStepX -= stepSize;
                Main.board.drawTile(playerX+1,playerY);
                Main.board.drawTile(playerX+1,playerY-1);
                xOffset = -1;
                yOffset = 0;
            } else {
                iStepX += stepSize;
                Main.board.drawTile(playerX-1,playerY);
                Main.board.drawTile(playerX-1,playerY-1);
                xOffset = 1;
                yOffset = 0;
            }
            Main.board.drawTile(playerX,playerY);
            Main.board.drawTile(playerX,playerY-1);
            playerHandler.display.playerTile(playerX, playerY, playerSprite, iStepX, iStepY);
        } else if (iStepY != 0) {
            if (iStepY > 0) {
                iStepY -= stepSize;
                Main.board.drawTile(playerX,playerY+1);
                xOffset = 0;
                yOffset = -1;
            } else {
                iStepY += stepSize;
                Main.board.drawTile(playerX,playerY-2);
                xOffset = 0;
                yOffset = 1;
            }
            Main.board.drawTile(playerX,playerY);
            Main.board.drawTile(playerX,playerY-1);
            playerHandler.display.playerTile(playerX, playerY, playerSprite, iStepX, iStepY);
        } else {
            if (Handler.kl.isPressed(KeyCode.W)) {
                setRotation(Direction.Up);
                playerMove(Direction.Up);
            } else if (Handler.kl.isPressed(KeyCode.A)) {
                setRotation(Direction.Left);
                playerMove(Direction.Left);
            } else if (Handler.kl.isPressed(KeyCode.S)) {
                setRotation(Direction.Down);
                playerMove(Direction.Down);
            } else if (Handler.kl.isPressed(KeyCode.D)) {
                setRotation(Direction.Right);
                playerMove(Direction.Right);
            } else {
                xOffset = 0;
                yOffset = 0;
            }
        }


        if (frameTimer <= 0) {
            animCounter += 1;
            frameTimer = 20;
        } else {
            frameTimer -= 1;
        }

        int index;
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
