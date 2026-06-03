package startgame;

public class GameController {

    private final GameEngine engine;

    public GameController(GameEngine engine) {
        this.engine = engine;
    }

    public GameEngine getEngine() {
        return engine;
    }

    public void startGame() {
        engine.init();
        engine.running = true;
    }

    public void setDifficulty(int delay) {
        engine.delay = delay;
    }

    public void setSound(boolean enabled) {
        engine.soundEnabled = enabled;
    }

    public void setSkin(String skin) {
        engine.skin = skin;
    }

    public void configureGame(
            int delay,
            String skin,
            boolean soundEnabled) {

        setDifficulty(delay);
        setSkin(skin);
        setSound(soundEnabled);
    }
}
