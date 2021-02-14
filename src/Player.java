import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Player implements GameObject{

    int[] playerSpriteBack = {1, 2};
    int[] playerSpriteBackIdle = {1, 2, 3, 4, 5, 6, 7, 8};
    int[] playerSpriteFront = {1, 2};
    int[] playerSpriteFrontIdle = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
    int[] playerSpriteLeft = {1, 2};
    int[] playerSpriteRight = {1, 2};


    private int playerHeight = 96;
    private int playerWidth = 48;
    private int counter = 0;
    private int moveCounter = 1;
    private int animCounter = 0;
    private final int ANIMSCALE = 10;
    private final int FOOTPRINTFREQ = 3;
    private Direction playerOrientation;
    private int playerX;
    private int playerY;
    private int xOffset = 0;
    private int yOffset = 0;
    private Handler playerHandler;
    private String playerSprite;
    int step = 6;

    Player(int X, int Y, Direction orientation, Handler handler){
        this.playerX = X;
        this.playerY = Y;
        this.playerOrientation = orientation;
        this.playerHandler = handler;
    }

    public void playerMove(Direction direction) {
        if (direction == Direction.Left) {
            xOffset = -1;
        } else if (direction == Direction.Right) {
            xOffset = 1;
        } else if (direction == Direction.Down) {
            yOffset = 1;
        } else if (direction == Direction.Up) {
            yOffset = -1;
        }

        setRotation(direction);
        playerY += yOffset*step;
        playerX += xOffset*step;
    }

    public void setRotation(Direction direction) {
        this.playerOrientation = direction;
    }

    public void display(Display d) {
        d.playerTile(playerX,playerY, playerSprite);
    }

    public void tick() {
        counter++;
        xOffset = 0;
        yOffset = 0;

        if(moveCounter % FOOTPRINTFREQ == 0) {
            getPlayerHandler().object.add(new Footprint(playerX, playerY, playerHandler));
            moveCounter = 1;
        }

        if (Handler.kl.isPressed(KeyCode.W)) {
            yOffset = -1;
            setRotation(Direction.Up);
        }
        if (Handler.kl.isPressed(KeyCode.A)) {
            xOffset = -1;
            setRotation(Direction.Left);
        }
        if (Handler.kl.isPressed(KeyCode.S)) {
            yOffset = 1;
            setRotation(Direction.Down);
        }
        if (Handler.kl.isPressed(KeyCode.D)) {
            xOffset = 1;
            setRotation(Direction.Right);
        }

        if(counter % ANIMSCALE == 0){
            animCounter ++;
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

        if(xOffset != 0 || yOffset != 0){
            moveCounter++;
        }

        if(!(playerY + 24 + yOffset*step < 0 || playerY + playerHeight + yOffset*step > Main.HEIGHT))
            playerY += yOffset*step;
        if(!(playerX + xOffset*step < 0 || playerX + playerWidth + xOffset*step > Main.WIDTH))
            playerX += xOffset*step;
    }

    public Direction getPlayerOrientation() {return playerOrientation;}

    public int getPlayerX() {return playerX;}

    public int getPlayerY() {return playerY;}

    public Handler getPlayerHandler() {return playerHandler;}

    public boolean isDead() {
        return false;
    }
}
