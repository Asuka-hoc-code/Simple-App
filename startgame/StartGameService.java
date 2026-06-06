package startgame;

import configuregame.GameConfig;

import javax.swing.*;

public class StartGameService {

    public void start(GameConfig config) {

        GameEngine engine = new GameEngine();

        engine.delay = config.delay;
        engine.soundEnabled = config.soundEnabled;
        engine.musicEnabled = config.musicEnabled;
        engine.multiplayer = config.multiplayer;

        engine.backgroundMusic = config.backgroundMusic;

        engine.player1HeadColor = config.player1HeadColor;
        engine.player1BodyColor = config.player1BodyColor;

        engine.player2HeadColor = config.player2HeadColor;
        engine.player2BodyColor = config.player2BodyColor;

        engine.init();

        JFrame frame = new JFrame("Snake Game");

        SnakeGameApp app =
                new SnakeGameApp(engine, frame);

        frame.add(app);

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        frame.setVisible(true);
    }
}
