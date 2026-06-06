
package configuregame;
import java.awt.*;

public class GameConfigController {

    private final GameConfig config;

    public GameConfigController() {

        config = new GameConfig();
    }

    public GameConfig getConfig() {

        return config;
    }

    // =========================
    // DIFFICULTY
    // =========================
    public void setDifficulty(int delay) {

        config.delay = delay;
    }

    // =========================
    // SOUND
    // =========================
    public void setSound(boolean enabled) {

        config.soundEnabled = enabled;
    }

    // =========================
    // MUSIC
    // =========================
    public void setMusic(boolean enabled) {

        config.musicEnabled = enabled;
    }

    // =========================
    // GAME MODE
    // =========================
    public void setMultiplayer(boolean multiplayer) {

        config.multiplayer = multiplayer;
    }

    // =========================
    // BACKGROUND MUSIC
    // =========================
    public void setBackgroundMusic(String music) {

        config.backgroundMusic = music;
    }

    // =========================
    // PLAYER SKIN
    // =========================
    public void setPlayer1Skin(Color head, Color body) {

        config.player1HeadColor = head;
        config.player1BodyColor = body;
    }

    public void setPlayer2Skin(Color head, Color body) {

        config.player2HeadColor = head;
        config.player2BodyColor = body;
    }
}

