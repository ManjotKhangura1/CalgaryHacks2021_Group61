
// import java.awt.Graphics;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

/**
 * This class contains every GameObject, and references to every active class.
 * Its purpose is to handle interactions between objects.
 * It also handles render and tick methods
 */

public class Handler {

    public Stage mainStage;

    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    public Player player = null;

    public static Keylist kl;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setup() {

    }

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick(); // update all
        }
    }

    public void render(Display d) {
        d.setupNextFrame();
        d.displayRectangle(10,10, 50, 50, Color.RED);
        d.setupNextFrame();
        d.drawGameFrame();
        Main.board.display(d);
        if (kl.isPressed(KeyCode.W)) {
            d.displayRectangle(10, 10, 10, 10, Color.RED);
        }
    }


}
