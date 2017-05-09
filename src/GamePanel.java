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
    JLabel question;
    public static int winSwitch = 0;
    public static int loseSwitch = 0;
    public static int winStay = 0;
    public static int loseStay = 0;
    public static int totalGames = 0;
    public static int firstDoorChosen = 0;
    public static int secondDoorChosen = 0;

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
            System.err.println("MEH");
            //System.out.println(e.getCause().toString());
        }

        for (int i = 0; i < Sim.NUM_DOORS; i++) {
            System.out.print(((ImageIcon)Sim.objects[i]).getDescription() + " ");
        }
        System.out.println();

        for (int i = 0; i < Sim.NUM_DOORS; i++) {
            JToggleButton btn = new JToggleButton();
            btn.setModel(new DoorModel());
            btn.setIcon(Sim.doors[i]);
            btn.setSelectedIcon(Sim.objects[i]);
            btn.addActionListener(new ActionListener() {
                /**
                 * Opens one door with goat behind it or final decision door.
                 * @param e the mouseClicked or mousePressed event.
                 */
                public void actionPerformed(ActionEvent e) {
                    //System.out.println(((ImageIcon)btn.getIcon()).getDescription());
                    openDoor(Integer.parseInt(((ImageIcon)btn.getIcon()).getDescription()));
                }
            });
            buttons.add(btn);
            add(btn);
        }

        congrats.setFont(new Font("Impact", Font.BOLD, 26));
        congrats.setForeground(Color.BLUE);
        congrats.setVisible(false);
        add(congrats);

        rip.setFont(new Font("Impact", Font.BOLD, 26));
        rip.setForeground(Color.RED);
        rip.setVisible(false);
        add(rip);

        JButton menu = new JButton("Back to Menu");
        menu.addActionListener(new ActionListener() {
            /**
             * Changes panel back to Main Menu.
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
        boolean done = false;

        if (clicks == 1) {
            firstDoorChosen = i;
            System.out.println("First door chosen: " + firstDoorChosen + 1);
        }
        if (clicks == 2) {
            secondDoorChosen = i;
            System.out.println("Second door chosen: " + secondDoorChosen + 1);
        }

        if (clicks <=  MAX_CLICKS-1) {
            for (int j = 0; j < Sim.NUM_DOORS && !done; j++) {
                if (j != i && ((ImageIcon) Sim.objects[j]).getDescription().equalsIgnoreCase("goat")) {
                    System.out.println("opening door: " + (j+1) + "\n");
                    buttons.get(j).setSelected(true);
                    buttons.get(j).setEnabled(false);
                    askContestant(i);
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
            question.setVisible(false);
            if (success) {
                if (firstDoorChosen == secondDoorChosen) winStay++;
                else winSwitch++;
                congrats.setVisible(true);
            } else {
                if (firstDoorChosen == secondDoorChosen) loseStay++;
                else loseSwitch++;
                rip.setVisible(true);
            }
        }

        totalGames++;

        System.out.println("Number of wins when we switch: " + winSwitch + "/" + totalGames);
        System.out.println("Number of wins when we stayed: " + winStay + "/" + totalGames);
        System.out.println("Number of loses when we switch: " + loseSwitch + "/" + totalGames);
        System.out.println("Number of loses when we stay: " + loseStay + "/" + totalGames);
    }

    /**
     * Asks contestant if they would like to stay or switch.
     * @param i the door number selected by the contestant.
     */
    public void askContestant(int i) {
        question = new JLabel("You chose DOOR " + (i+1) + ". You can switch or stay.", SwingConstants.CENTER);
        question.setFont(new Font("Impact", Font.BOLD, 26));
        question.setForeground(Color.magenta);
        question.setVisible(true);
        add(question);
        validate();
    }

    /**
     * Shuffles goats and car.
     */
    public void shuffleObjects() {
        Random rand = new Random();
        int j;
        for (int i = Sim.NUM_DOORS-1; i>0; i--) {
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
