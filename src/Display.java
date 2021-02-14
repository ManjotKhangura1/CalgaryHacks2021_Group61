
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.scene.image.Image;

/**
 * This class displays everything on screen (the GUI). It is called in a lot of the other classes whenever anything
 * needs to be displayed
 */
public class Display {

    private int pixelScale = 48;

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
        Image image = null;
        if(t.getTileID() == TileID.Driveway){
            String prefix = "images/Driveway/";
            String postfix = ".png";
            image = new Image(prefix + t.getVariant() + postfix);
        }
        else if(t.getTileID() == TileID.Environment){
            image = new Image("images/Environment01.png");
        }
        else if(t.getTileID() == TileID.House){
            image = new Image("images/House.png");
        }
        else if(t.getTileID() == TileID.RoadTop){
            String prefix = "images/RoadSpriteTop/";
            String postfix = ".png";
            image = new Image(prefix + t.getVariant() + postfix);
        }
        else if(t.getTileID() == TileID.RoadBottom){
            String prefix = "images/RoadSpriteBottom/";
            String postfix = ".png";
            image = new Image(prefix + t.getVariant() + postfix);
        }
        gC.drawImage(image, x*pixelScale, y*pixelScale);
        //gC.fillRect(x*pixelScale, y*pixelScale, pixelScale, pixelScale);
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
}
