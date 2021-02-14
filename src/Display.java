
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.scene.image.Image;
import sun.security.ec.ECDSAOperations;

import java.util.Random;

/**
 * This class displays everything on screen (the GUI). It is called in a lot of the other classes whenever anything
 * needs to be displayed
 */
public class Display {

    public int pixelScale = 48;

    /**
     * Instance variable to call on the canvas of javafx to draw objects
     */
    private GraphicsContext gC;

    /**
     * Constructor for display class
     * @param gC - graphics from canvas that will be drawn
     */
    public Display(GraphicsContext gC) {
        this.gC = gC;
    }

    /*public static int randomInteger() {
        Random randomNumber = new Random();
        int randNum = randomNumber.nextInt(20);
        return randNum;
    }*/

    public void displayTile(Tile t, int x, int y) {
        // Takes the tile, and the x,y coordinates
        // Determines the actual screen position to draw the tile, and draws ità
        Image image = new Image("images/RoadSprite001.png");
        if(t.getTileID() == TileID.Driveway){
            if (t.getSnowLevel() == 3)
            {
                image = new Image("images/snowlevel3.png");
                gC.setFill(Color.DARKGREY);
            }
            else if (t.getSnowLevel() == 2)
            {
                image = new Image("images/snowlevel2.png");
                gC.setFill(Color.DARKGREY);
            }
            else if (t.getSnowLevel() == 1)
            {
                image = new Image("images/snowlevel1.png");
                gC.setFill(Color.DARKGREY);
            }
            else {
                image = new Image("images/Driveway.png");
                gC.setFill(Color.DARKGREY);
            }
        }
        else if(t.getTileID() == TileID.Environment){
            /*int randNum = 0;
            if(randNum == 1){image = new Image("images/env1.png");}
            if(randNum == 2){image = new Image("images/env2.png");}
            if(randNum == 3){image = new Image("images/env3.png");}
            if(randNum == 4){image = new Image("images/env4.png");}
            if(randNum == 5){image = new Image("images/env5.png");}
            if(randNum == 6){image = new Image("images/env6.png");}*/
                image = new Image("images/Environment01.png");
            gC.setFill(Color.DARKOLIVEGREEN);
        }
        else if(t.getTileID() == TileID.House){
            image = new Image("images/House.png");
            gC.setFill(Color.SADDLEBROWN);
        }
        else if(t.getTileID() == TileID.Road){
            image = new Image("images/RoadSprite001.png");
            gC.setFill(Color.GREY);
        }
        gC.drawImage(image, x*pixelScale, y*pixelScale);
        //gC.fillRect(x*pixelScale, y*pixelScale, pixelScale, pixelScale);
    }

    public void playerTile(int x, int y, String sprite, int offX, int offY) {
        Image image = new Image(sprite);
        gC.drawImage(image, x*pixelScale + offX, y*pixelScale + offY - pixelScale);
    }


    public void redTile(int x, int y, int offX, int offY) {
        displayRectangle(x*pixelScale + offX, y*pixelScale + offY, pixelScale, pixelScale, Color.RED);
    }

    // Example
    public void displayRectangle(int x, int y, int width, int height, Color color) {
        gC.setFill(color);
        gC.fillRect(x, y, width, height);
    }


    public void setupNextFrame() {
        gC.setFill(Color.BLACK);
        gC.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
    }

    public void drawGameFrame(){
        Main.board.display(this);
    }

    public void drawIntroFrame(){

    }

    /**
     * Creates a display for a button of a specific size and color (called primarily in the menu class)
     * @param x - the x coordinate of the top left corner of the button
     * @param y - the y coordinate of the top left corner of the button
     * @param width - The width of the button
     * @param height - the height of the button
     * @param color - the color of the button
     * @param text - text that will show on the button
     */
    public void displayButton(int x, int y, int width, int height, Color color, String text) {

        //Sets color
        gC.setFill(color);

        //Creates a rectangle of a specific width and height
        gC.fillRect(x, y, width, height);

//		Sets the color of the button depending on the brightness of the text so that a dark text and dark background
//		or light text and light background don't coincide and make things unreadable
        if (color.getBrightness() < Color.DARKGREY.getBrightness())
        {
            gC.setFill(Color.WHITE);
        }
        else
        {
            gC.setFill(Color.BLACK);
        }
        gC.fillText(text, x + (width/2.3), y + (height/2));
    }

}
