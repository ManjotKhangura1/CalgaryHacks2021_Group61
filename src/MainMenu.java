//package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Creates main menu when game loads up
 */


public class MainMenu extends Handler{

    /**
     * State of the menu
     */
    private static MenuState state = MenuState.mainmenu;
    /**
     * List of buttons
     */
    private static LinkedList<Button> buttonList;
    /**
     * handler object
     */
    private Handler handler;
    /**
     * stage used for load function
     */
    private Stage mainStage;
    /**
     * Boolean that tells the display when and when not to update
     */
    private boolean timeToUpdate = true;


    /**
     * Constructor for the Main Menu
     * @param h - Handler that executes the program
     * @param mainStage - Window for file viewer
     */
    public MainMenu(Handler h, Stage mainStage) {
        buttonList = new LinkedList<Button>();
        this.handler = h;
        this.mainStage = mainStage;
    }

    /**
     * Method that adds buttons to display list
     */
    public void render(Display d) {
        for (Button b:buttonList) {
            b.render(d);
        }
    }

    /**
     * method that Updates and controls which buttons are displayed
     */
    public void update() {
        if (!timeToUpdate)
        {
            return;
        }
        this.timeToUpdate = false;
        reset();
        if (state == MenuState.mainmenu) {
            //renderMainMenu();
        }
        else if (state == MenuState.playmenu) {
            //renderPlayMenu();
        }
        renderPlayMenu();
    }

    /**
     * Method that clears the button list
     */
    public void reset() {
        while (buttonList.size() > 0) {
            buttonList.remove();
        }
    }
    /**
     * method that adds a button to the list to be displayed
     * @param b - Newly created button.
     */
    public void addButton(Button b) {
        b.setMenu(this);
        buttonList.add(b);
    }
    /**
     * method that changes the game state to Play, it changes from main to play.
     */
    public void renderPlayMenu() {
        handler.setGameStatePlay();

    }
    /**
     * method that creates the buttons for the Main menu.
     */
    public void renderMainMenu() {
        Color c = Color.color(0, 0.5, 0.5);

        addButton(new Button(450,180,400,100,c,"Press P to play"));

        addButton(new Button(450,320,400,100,c,"Quit"));


    }

}
