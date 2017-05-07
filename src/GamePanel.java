import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.ArrayList;

/**
 * The actual game.
 * Generates doors, goats, and car.
 *
 * @author Heather Bradfield
 * @version 05/06/2017
 */
public class GamePanel extends JPanel {
    JLabel congrats = new JLabel("YOU WIN!", SwingConstants.CENTER);
    JLabel rip = new JLabel("YOU LOSE", SwingConstants.CENTER);

    ArrayList<JToggleButton> buttons = new ArrayList<>();
    int clicks = 0;
    public static final int MAX_CLICKS = 2;

    /**
     * Custom model for JToggleButton doors.
     */
    public class DoorModel extends JToggleButton.ToggleButtonModel {
        public void reset() {
            super.setSelected(false);
        }

        @Override
        public void setSelected(boolean b) {
            if (clicks >= 1 && clicks < MAX_CLICKS && !isSelected()) {
                super.setSelected(b);
            }
        }
    }
    /**
     * Creates an ArrayList of door buttons with goat or car behind each.
     * Displays images and makes logic checks (user clicked on car or goat).
     * @param newSim The Monty Hall Sim applet.
     */
    public GamePanel(final Sim newSim) {
        clicks = 0;

        try {
            shuffleObjects();
        }
        catch (Exception e) {
            System.err.println("Error shuffling objects");
        }

        for (int i = 0; i < Sim.NUM_DOORS; i++) {
            JToggleButton btn = new JToggleButton();
            btn.setModel(new DoorModel());
            btn.setIcon(Sim.doors[i]);
            btn.setSelectedIcon(Sim.objects[i]);
            btn.addActionListener(new ActionListener() {
                /**
                 * Opens one door with goat behind it or final decision door.
                 *
                 * @param e the mouseClicked or mousePressed event.
                 */
                public void actionPerformed(ActionEvent e) {
                    System.out.println(((ImageIcon)btn.getIcon()).getDescription());
                    openDoor(Integer.parseInt(((ImageIcon)btn.getIcon()).getDescription()));
                }
            });
            buttons.add(btn);
            add(btn);
        }

        congrats.setFont(new Font("Chalkboard", Font.BOLD, 26));
        congrats.setForeground(Color.BLUE);
        congrats.setVisible(false);
        add(congrats);

        rip.setFont(new Font("Chalkboard", Font.BOLD, 26));
        rip.setForeground(Color.RED);
        rip.setVisible(false);
        add(rip);

        JButton menu = new JButton("Back to Menu");
        menu.addActionListener(new ActionListener() {
            /**
             * Changes panel back to Main Menu.
             *
             * @param e the mouseClicked or mousePressed event.
             */
            public void actionPerformed(ActionEvent e) {
                newSim.createGUI();  // used to generate an entire new game
                // not the best practice, but was not given enough time
                // to change how the previous group generated a new game.
                newSim.swapCard(Sim.menuPan);
            }
        });
        add(menu);
    }

    /**
     * Checks which door was chosen and if not final decision click, opens goat door.
     * @param i index of door.
     */
    public void openDoor(int i) {
        clicks++;
        System.out.println(i);
        boolean done = false;
        if (clicks <=  MAX_CLICKS-1) {
            for (int j = 0; j < Sim.NUM_DOORS && !done; j++) {
                if (j != i && ((ImageIcon) Sim.objects[j]).getDescription().equalsIgnoreCase("goat")) {
                    System.out.println("opening door: " + (j+1) + "\n");
                    buttons.get(j).setSelected(true);
                    done = true;
                }
            }
        }
        else {
            gameOver(((ImageIcon)Sim.objects[i]).getDescription().equalsIgnoreCase("car"));
        }
    }

    /**
     * Displays results.
     * @param success whether the contestant won or not.
     */
    public void gameOver(boolean success) {
        if (clicks == MAX_CLICKS) {
            if (success) {
                congrats.setVisible(true);
            } else {
                rip.setVisible(true);
            }
        }
    }

    /**
     * Asks contestant if they would like to stay or switch.
     */
    public void askContestant() {

    }

    /**
     * Shuffles goats and car.
     */
    public void shuffleObjects() {
        Random rand = new Random();
        int j;
        for (int i = Sim.NUM_DOORS-1; i>=0; i--) {
            j = rand.nextInt() % (i+1);
            swap(i,j);
        }
    }

    /**
     * Swaps two objects.
     */
    public void swap(int i, int j){
        Icon temp = Sim.objects[i];
        Sim.objects[i] = Sim.objects[j];
        Sim.objects[j] = temp;
    }
}
