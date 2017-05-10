import javax.swing.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class that sets up the main menu and its attributes.
 *
 * @author Heather Bradfield
 * @version 09/19/2016
 */
public class MenuPanel extends JPanel {
    /**
     * Adds game title and menu buttons to the main panel.
     * @param newSim The game applet.
     */
    public MenuPanel(final Sim newSim){

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        setBackground(newSim.lightBlue);

        JLabel stewie = new JLabel(new ImageIcon(getClass().getResource("Images/MontyHall.png")), SwingConstants.CENTER);
        add(stewie, gbc);

        JLabel name = new JLabel("<html>Monty Hall Simulation<br></html>",SwingConstants.CENTER);
        name.setFont(new Font("Impact", Font.BOLD, 80));
        name.setForeground(newSim.oceanBlue);
        add(name);

        JButton game = new JButton("Play");
        game.addActionListener(new ActionListener(){
            /**
             * Starts game.
             * @param e The mouseClicked or mouseClicked event.
             */
            public void actionPerformed(ActionEvent e){
                newSim.swapCard(Sim.gamePan);
            }
        });
        add(game);

        JButton stats = new JButton("Statistics");
        stats.addActionListener(new ActionListener(){
            /**
             * Starts game.
             * @param e The mouseClicked or mouseClicked event.
             */
            public void actionPerformed(ActionEvent e){
                newSim.swapCard(Sim.statsPan);
            }
        });
        add(stats);

        JButton quit = new JButton("Quit");
        quit.addActionListener(new ActionListener(){
            /**
             * Terminates the applet.
             * @param e The mouseClicked or mouseClicked event.
             */
            public void actionPerformed(ActionEvent e){
                System.exit(0); //change so it exits browser
            }
        });
        add(quit);
    }
}


