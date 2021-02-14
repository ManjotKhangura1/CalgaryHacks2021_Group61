
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;

import javafx.scene.image.Image;

import javax.swing.*;

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

    public void displayTile(Tile t, int x, int y) {
        // Takes the tile, and the x,y coordinates
        // Determines the actual screen position to draw the tile, and draws ità
        Image image = new Image("images/RoadSprite001.png");
        if(t.getTileID() == TileID.Driveway){
            image = new Image("images/Driveway.png");
            gC.setFill(Color.DARKGREY);
        }
        else if(t.getTileID() == TileID.Environment){
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

    public void footprint(int x, int y, double opacity) {
        gC.setGlobalAlpha(opacity);
        gC.setEffect(new BoxBlur(10, 10, 3));
        gC.setFill(Color.color(Math.abs((double)x/Main.WIDTH), Math.abs((double)y/Main.HEIGHT), Math.abs(opacity)));
        gC.fillOval(x+16, y+90, 40, 40);
        gC.setEffect(null);
        gC.setGlobalAlpha(1.0);
    }

    public void playerTile(int x, int y, String sprite) {
        Image image = new Image(sprite);
        gC.drawImage(image, x, y);
    }

    // Examples
    public void displayRectangle(int x, int y, int width, int height, Color color) {
        gC.setFill(color);
        gC.fillRect(x, y, width, height);
    }


    public void setupNextFrame() {
        gC.setFill(Color.BLACK);
        gC.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
    }

    public void drawIntroFrame(){

    }

}
