
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * The Game object. It instantiates many of the key classes.
 */

public class Main extends Application {

    /**
     * The width of the window
     */
    public static final int WIDTH = 1360;

    /**
     * The height of the window
     */
    public static final int HEIGHT = 960;

    /**
     * The width of the arena
     */
    public static int arenaWidth = 20;

    /**
     * The height of the arena
     */
    public static int arenaHeight = 20;

    /**
     * The canvas that fills the window
     */
    private Canvas mainCanvas;


    private Keylist kL;

    /**
     * The game's scene
     */
    private Scene gameScene;

    public static Board board;

    public static AudioPlayer player = AudioPlayer.player;
    public static AudioStream song;

    public void setupKeylist(Scene gameScene) {
        kL = new Keylist();
        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, kL);
        gameScene.addEventHandler(KeyEvent.KEY_RELEASED, kL);
    }

    /**
     * Sets everything up, and starts the game.
     * @param mainStage the stage to make things on.
     */
    public void start(Stage mainStage) throws Exception {
        Handler handler = new Handler();

        setupWindow(mainStage);

        Display display = setupDisplay();

        handler.setMainStage(mainStage);
        handler.setup();

        AnimationController aC = new AnimationController(handler, display);

        aC.start();
        mainStage.show();
        mainStage.centerOnScreen();
        mainStage.setResizable(false);

        board = new Board();
        board.setup();
        setupKeylist(gameScene);
        Handler.kl = kL;

    }

    @Override
    public void stop() {
        player.stop(song);
    }

    /**
     * Creates an instance of the Display class
     * @return The created instance.
     */
    public Display setupDisplay() {
        GraphicsContext gC = mainCanvas.getGraphicsContext2D();
        return new Display(gC);
    }

    /**
     * Creates a window, with a canvas
     * @param mainStage The mainstage to use.
     */
    public void setupWindow(Stage mainStage) {
        Group root = new Group();
        mainCanvas = new Canvas(WIDTH,HEIGHT);
        root.getChildren().add(mainCanvas);


        gameScene = new Scene(root, 0, 0);


        mainStage.setWidth(Main.WIDTH);
        mainStage.setHeight(Main.HEIGHT+39);

        mainStage.setTitle("Game");
        mainStage.setScene(gameScene);
    }

    public static void backgroundMusic() throws IOException {

        InputStream songFile = new FileInputStream("out/production/CalgaryHacks2021_Group61/images/file3.mid");

        song = new AudioStream(songFile);

        AudioPlayer.player.start(song);

        //player.start(loop);

    }

    /**
     * The main method.
     * @param args The arguments passed to the program.
     */
    public static void main(String[] args) throws IOException {
        System.out.println("start");
        backgroundMusic();
        launch(args);
        System.out.println("done");
    }
}
