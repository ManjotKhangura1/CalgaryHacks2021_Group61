
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
        if (kl.justPressed(KeyCode.P) || kl.justPressed(KeyCode.ENTER))
        {
            setGameStatePlay();
        }
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick(); // update all
        }
    }

    /**
     * The instance of the menu
     */
    private MainMenu menu = null;

    /**
     * The current game state
     */
    private GameState gState = GameState.MainMenu;

    /**
     * Sets the game state to play
     */
    public void setGameStatePlay() {
        gState = GameState.Play;
    }

    /**
     * Sets the game state to main menu
     */
    public void setGameStateMainMenu() {
        gState = GameState.MainMenu;
    }

    public void render(Display d) {
        display = d;
        if (gState == GameState.MainMenu) {
            d.setupNextFrame();
            if (menu == null) {
                menu = new MainMenu(this, mainStage);
            }
            menu.update();
            menu.render(d);
            //implement soon
        }
        else if (gState == GameState.Play) {
            if (!hasRendered || kl.justPressed(KeyCode.SPACE)) {
                d.drawGameFrame();
                hasRendered = true;
            }
            player.display(d);
        }
    }
}
