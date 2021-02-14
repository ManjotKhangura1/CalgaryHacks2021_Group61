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
     * Method that controls click locations
     */
    public void recieveClick() {
        for (Button b:buttonList) {
            b.menuTriggerClick();
        }

    }

    /**
     * method that sets the menu state
     * @param state - Menu State you want to change to.
     */
    public void setState(MenuState state) {
        MainMenu.state = state;
        this.timeToUpdate = true;
    }

    /**
     * Method that takes name of button and executes action
     * @param text - Name of the button.
     */
    public void menuClickHandler(String text) {
        if(text == "Play game") {
            setState(MenuState.playmenu);
        }
        else if (text == "Load game") {
            setState(MenuState.loadmenu);
        }
        else if (text == "Options") {
            setState(MenuState.optionsmenu);
        }
        else if (text == "Quit") {
            System.exit(1);
        }
        else if (text == "Back") {
            setState(MenuState.mainmenu);
        }

        else if (text == "^") {
            Main.arenaHeight = Main.arenaHeight + 100;
            Main.arenaWidth = Main.arenaWidth + 100;
            handler.setup();
        }
        else if (text == "v") {
            Main.arenaHeight = Main.arenaHeight - 100;
            Main.arenaWidth = Main.arenaWidth - 100;
            handler.setup();
        }
        else if (text == "Defaults") {
            Main.arenaHeight = 1500;
            Main.arenaWidth = 2000;
            handler.setup();
        }
        else if (text == "^ ") {
            handler.setup();
        }
        else if (text == "v ") {
            handler.setup();
        }
        else if (text == "Defaults ") {
            handler.setup();
        }
        else if (text == "^  ") {
            handler.setup();
        }
        else if (text == "v  ") {
            handler.setup();
        }
        else if (text == "Defaults  ") {
            handler.setup();
        }
        this.timeToUpdate = true;
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
            renderMainMenu();
        }
        else if (state == MenuState.loadmenu) {
            renderLoadMenu();
        }
        else if (state == MenuState.optionsmenu) {
            renderOptionsMenu();
        }
        else if (state == MenuState.playmenu) {
            renderPlayMenu();
        }
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
     * method that creates the buttons for the options menu
     */
    public void renderOptionsMenu() {
        Color cc = Color.color(0, 0, 0.5);

        addButton(new Button(500,30,100,100,cc,"^"));

        addButton(new Button(610,30,100,100,cc,"v"));

        addButton(new Button(750,30,150,100,cc,"Defaults"));

        addButton(new Button(500,210,100,100,cc,"^ "));

        addButton(new Button(610,210,100,100,cc,"v "));

        addButton(new Button(750,210,150,100,cc,"Defaults "));

        addButton(new Button(500,390,100,100,cc,"^  "));

        addButton(new Button(610,390,100,100,cc,"v  "));

        addButton(new Button(750,390,150,100,cc,"Defaults  "));

        addButton(new Button(280,570,400,100,cc,"Back"));

    }
    /**
     * method that creates the buttons for the load menu
     */
    public void renderLoadMenu() {
        Color c = Color.color(0, 0.5, 0);

        addButton(new Button(280,30,400,100,c,"Pick File"));

        addButton(new Button(280,570,400,100,c,"Back"));
    }
    /**
     * method that creates the buttons for the Main menu.
     */
    public void renderMainMenu() {
        Color c = Color.color(0, 0.5, 0.5);

        addButton(new Button(280,30,400,100,c,"Play game"));

        addButton(new Button(280,210,400,100,c,"Load game"));

        addButton(new Button(280,390,400,100,c,"Options"));

        addButton(new Button(280,570,400,100,c,"Quit"));


    }

}
