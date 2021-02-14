
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

    boolean hasRendered = false;

    public LinkedList<GameObject> object = new LinkedList<>();
    public Player player = null;

    public static Keylist kl;
    public Display display;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setup() {
        player = new Player(10, 10, Direction.Left, this);
        object.add(player);
    }

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick(); // update all
        }
    }

    public void render(Display d) {
        display = d;
        if (!hasRendered || kl.justPressed(KeyCode.SPACE)) {
            d.setupNextFrame();
            hasRendered = true;
        }
        d.setupNextFrame();
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.display(d); // display all

            if(tempObject.isDead())
                object.removeFirstOccurrence(tempObject);
        }
        player.display(d);
    }

    public void addFootprint(Footprint footprint){
        object.add(footprint);
    }
}
