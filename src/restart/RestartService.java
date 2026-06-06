package restart;
import startgame.GameEngine;

public class RestartService {
    private final GameResetManager resetManager = new GameResetManager();
    public void restart(GameEngine engine) {
        resetManager.resetState(engine);
    }
}