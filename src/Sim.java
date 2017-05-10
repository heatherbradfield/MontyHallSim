/**
 * Created by Heather on 5/6/17.
 */
//import com.sun.org.glassfish.external.statistics.Stats;

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
    public static final String statsPan = "Statistics";
    public static int num_games = 0;
    public static Color brown = new Color(213,196,174);
    public static Color lightBrown = new Color(244,236,226);
    public static Color grey = new Color(227,230,228);
    public static Color lightBlue = new Color(190,226,228);
    public static Color oceanBlue = new Color(111,211,223);
    public static Color bloodRed = new Color(150,0,0);

    public void init() {
        doors = new Icon[NUM_DOORS];
        objects = new Icon[NUM_DOORS];
        door_open = new ImageIcon(getClass().getResource("Images/door_open.png"));

        String door_str = "Images/door_close";
        ImageIcon icon;
        for (int i = 0; i < NUM_DOORS; i++) {
            icon = new ImageIcon(getClass().getResource(door_str + (i+1) + ".png"));
            icon.setDescription(i + "");
            doors[i] = icon;
            if (i < NUM_DOORS-1)
            {
                icon = new ImageIcon(getClass().getResource("Images/goat3.png"));
                icon.setDescription("goat");
                objects[i] = icon;
            }
            else
            {
                icon = new ImageIcon(getClass().getResource("Images/car3.png"));
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
            System.err.println("\"createGUI\" didn't successfully complete");
            System.exit(0);
        }
    }

    public void createGUI() {
        MenuPanel menu = new MenuPanel(this);
        GamePanel game = new GamePanel(this);
        StatsPanel stats = new StatsPanel(this);

        mainPanel.add(menu, menuPan);
        mainPanel.add(game, gamePan);
        mainPanel.add(stats, statsPan);
        getContentPane().add(mainPanel);
    }

    public void swapCard(String name) {
        cardlayout.show(mainPanel, name);
    }
}
