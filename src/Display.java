
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class displays everything on screen (the GUI). It is called in a lot of the other classes whenever anything
 * needs to be displayed
 */
public class Display {

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
