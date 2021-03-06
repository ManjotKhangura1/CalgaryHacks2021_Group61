
import javafx.scene.paint.Color;


public class Button {

    /**
     * Coordinates and size of the button
     */
    private int x,y,width,height;
    /**
     * color of the button
     */
    private Color color;
    /**
     * Name and text displayed by the button
     */
    private String text;
    /**
     * object of the Main menu
     */
    private MainMenu menu;

    /**
     * Constructor for our buttons
     * @param x - coordinate of the button on the Horizontal plane
     * @param y - coordinate of the button on the Vertical plane
     * @param width - the length of the button on the Horizontal plane
     * @param height - the length of the button on the Vertical plane
     * @param color - The color of the button
     * @param text - Name displayed on button
     */
    public Button(int x, int y, int width, int height, Color color, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.text = text;
    }

    /**
     * setter for MainMenu
     * @param menu - MainMenu object
     */
    public void setMenu(MainMenu menu) {
        this.menu = menu;
    }
    /**
     * Method that is used to display the button
     * @param d - Display object
     */
    public void render (Display d) {
        d.displayButton(x, y, width, height, color, text);
    }


}
