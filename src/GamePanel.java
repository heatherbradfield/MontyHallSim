import javax.swing.*;
import java.applet.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Random;
import java.util.ArrayList;

/**
 * The actual game.
 * Generates animal images and sounds.
 *
 * @author Heather Bradfield
 * @version 09/19/2016
 */
public class GamePanel extends JPanel {
    JLabel congrats = new JLabel("YOU WIN!", SwingConstants.CENTER);
    JLabel rip = new JLabel("YOU LOSE", SwingConstants.CENTER);
    ArrayList<JToggleButton> buttons = new ArrayList<>();
    int clicks = 0;
    public static final int MAX_CLICKS = 2;

    public class DoorModel extends JToggleButton.ToggleButtonModel {
        public void reset() {
            super.setSelected(false);
        }

        @Override
        public void setSelected(boolean b) {
            if (clicks >= 1 && clicks < MAX_CLICKS + 1) {
                super.setSelected(b);
            }
        }
    }
    /**
     * Creates a hash map of animal names and their corresponding sounds.
     * Creates a sound button and plays a random animal sound.
     * Displays images and makes logic checks (user clicked on correct animal).
     * @param newSim The Animal Sounds applet.
     */

    public GamePanel(final Sim newSim) {

//        congrats.setFont(new Font("Chalkboard", Font.BOLD, 26));
//        congrats.setForeground(Color.BLUE);
//        congrats.setVisible(false);
//        add(congrats);

        shuffleObjects();
        for (int i = 0; i < Sim.NUM_DOORS; i++) {
            JToggleButton btn = new JToggleButton();
            btn.setModel(new DoorModel());
            btn.setIcon(Sim.doors[i]);
            btn.setSelectedIcon(Sim.objects[i]);
            btn.addActionListener(new ActionListener() {
                /**
                 * Changes panel back to Main Menu.
                 *
                 * @param e the mouseClicked or mousePressed event.
                 */
                public void actionPerformed(ActionEvent e) {
                    //open_door = true;
                    clicks++;
                    System.out.println(((ImageIcon)btn.getIcon()).getDescription());
                    openDoor(Integer.parseInt(((ImageIcon)btn.getIcon()).getDescription().replaceAll("\\D+","")));
                }
            });
            buttons.add(btn);
            add(btn);
        }


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

    public void openDoor(int i) {
        System.out.println(i);
        boolean done = false;
        if (clicks <  MAX_CLICKS) {
            for (int j = 0; j < Sim.NUM_DOORS && !done; j++) {
                if (j != (i-1) && ((ImageIcon) Sim.objects[j]).getDescription().equalsIgnoreCase("goat")) {
                    System.out.println("opening door: " + (j+1) + "\n");
                    buttons.get(j).setSelected(true);
                    done = true;
                }
            }
        }
    }

    public void askContestant() {

    }

    public void shuffleObjects() {
        Random rand = new Random();
        int j;
        for (int i = Sim.NUM_DOORS-1; i>=0; i--) {
            j = rand.nextInt() % (i+1);
            swap(i,j);
        }
    }

    public void swap(int i, int j){
        Icon temp = Sim.objects[i];
        Sim.objects[i] = Sim.objects[j];
        Sim.objects[j] = temp;
    }
}
