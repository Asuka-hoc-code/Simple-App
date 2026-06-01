package move;

import startgame.GameEngine;

import java.awt.*;

public class MoveController {

    private MovementValidator validator =
            new MovementValidator();

    private GameEngine engine;

    public MoveController(GameEngine engine) {
        this.engine = engine;
    }

    public void moveSnake() {

        if (validator.isIdle(engine.dx, engine.dy)) {
            return;
        }

        Point head =
                engine.snake.get(0);

        Point newHead =
                validator.computeNewHead(
                        head,
                        engine.dx,
                        engine.dy
                );

        engine.snake.add(0, newHead);

        engine.snake.remove(
                engine.snake.size() - 1
        );
    }
}