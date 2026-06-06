package restart;

import configuregame.ConfigureGameUI;

import javax.swing.*;

public class BackToMenuService {

    public void backToMenu(JFrame currentFrame) {

        // Close current game window
        currentFrame.dispose();

        // Open configure menu again
        ConfigureGameUI menu =
                new ConfigureGameUI();

        menu.setVisible(true);
    }
}

