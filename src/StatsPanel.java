/**
 * Created by kristen on 5/9/17.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatsPanel extends JPanel {
    /**
     * Adds game title and menu buttons to the main panel.
     * @param newSim The game applet.
     */
    public StatsPanel(final Sim newSim){

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        setBackground(Sim.lightBlue);

        JLabel totalGames = new JLabel("Total Games: " + Integer.toString(GamePanel.totalGames), SwingConstants.CENTER);
        totalGames.setFont(new Font("Impact", Font.BOLD, 32));
        totalGames.setForeground(Color.WHITE);
        add(totalGames,gbc);

        JLabel winSwitch = new JLabel("Number of wins when player switched: " + Integer.toString(GamePanel.winSwitch), SwingConstants.CENTER);
        winSwitch.setFont(new Font("Impact", Font.BOLD, 32));
        winSwitch.setForeground(Color.BLUE);
        add(winSwitch,gbc);

        JLabel winStay = new JLabel("Number of wins when player stayed: " + Integer.toString(GamePanel.winStay), SwingConstants.CENTER);
        winStay.setFont(new Font("Impact", Font.BOLD, 32));
        winStay.setForeground(Color.BLUE);
        add(winStay,gbc);

        JLabel loseSwitch = new JLabel("Number of losses when player switched: " + Integer.toString(GamePanel.loseSwitch), SwingConstants.CENTER);
        loseSwitch.setFont(new Font("Impact", Font.BOLD, 32));
        loseSwitch.setForeground(Sim.bloodRed);
        add(loseSwitch,gbc);

        JLabel loseStay = new JLabel("Number of losses when player stayed: " + Integer.toString(GamePanel.loseStay), SwingConstants.CENTER);
        loseStay.setFont(new Font("Impact", Font.BOLD, 32));
        loseStay.setForeground(Sim.bloodRed);
        add(loseStay,gbc);

        JButton menu = new JButton("Back to Menu");
        menu.addActionListener(new ActionListener() {
            /**
             * Changes panel back to Main Menu.
             * @param e the mouseClicked or mousePressed event.
             */
            public void actionPerformed(ActionEvent e) {
                newSim.swapCard(Sim.menuPan);
            }
        });
        add(menu);
    }
}
