package restart;

import startgame.GameEngine;

public class GameResetManager {
    public void resetState(GameEngine engine) {
        engine.init();
        engine.running = true;
    }
}