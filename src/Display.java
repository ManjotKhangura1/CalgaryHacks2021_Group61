
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.scene.image.Image;

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
        gC.fillText(text, x + (width/2), y + (height/2));
    }

}
