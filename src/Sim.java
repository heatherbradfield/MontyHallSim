/**
 * Created by Heather on 5/6/17.
 */
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import java.awt.*;
import javax.swing.*;


public class Sim extends JApplet {
    public static Icon[] doors;
    public static Icon[] objects;
    public static Icon door_open;
    public static final int NUM_DOORS = 3;
    public CardLayout cardlayout = new CardLayout();
    public JPanel mainPanel = new JPanel(cardlayout);
    public static final String menuPan = "Menu";
    public static final String gamePan = "Game";

    public void init() {
        doors = new Icon[NUM_DOORS];
        objects = new Icon[NUM_DOORS];
        door_open = new ImageIcon(getClass().getResource("Images/door_open.png"));

        String door_str = "Images/door_close";
        ImageIcon icon;
        for (int i = 0; i < NUM_DOORS; i++) {
            doors[i] = new ImageIcon(getClass().getResource(door_str + (i+1) + ".png"));
            if (i < NUM_DOORS-1)
            {
                icon = new ImageIcon(getClass().getResource("Images/goat.png"));
                icon.setDescription("goat");
                objects[i] =icon;
            }
            else
            {
                icon = new ImageIcon(getClass().getResource("Images/car.png"));
                icon.setDescription("car");
                objects[i] = icon;
            }
        }
        try
        {
            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    resize(1024,768);
                    createGUI();
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't successfully complete");
            System.exit(0);
        }
    }

    public void createGUI() {
        MenuPanel menu = new MenuPanel(this);
        GamePanel game = new GamePanel(this);

        mainPanel.add(menu, menuPan);
        mainPanel.add(game, gamePan);
        getContentPane().add(mainPanel);
    }

    public void swapCard(String name) {
        cardlayout.show(mainPanel, name);
    }
}
