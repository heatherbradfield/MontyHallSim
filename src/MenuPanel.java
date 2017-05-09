import javax.swing.*;
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

        setBackground(Color.BLACK);

        JLabel name = new JLabel("Monty Hall Simulation",SwingConstants.CENTER);
        name.setFont(new Font("Impact", Font.BOLD, 32));
        name.setForeground(Color.BLUE);
        add(name,gbc);

        JButton game = new JButton("Play (3 Doors)");
        game.addActionListener(new ActionListener(){
            /**
             * Starts game.
             * @param e The mouseClicked or mouseClicked event.
             */
            public void actionPerformed(ActionEvent e){
                newSim.swapCard(Sim.gamePan);
            }
        });
        add(game,gbc);

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
        add(stats,gbc);

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
        add(quit,gbc);
    }
}


