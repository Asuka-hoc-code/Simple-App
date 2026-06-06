package move;

import startgame.GameEngine;
import startgame.PlayerSnake;

import java.awt.*;

public class MoveController {

    private final MovementValidator validator =
            new MovementValidator();

    private final GameEngine engine;

    public MoveController(GameEngine engine) {
        this.engine = engine;
    }

    public void moveSnake(PlayerSnake snake) {

        // =====================
        // STOP IF IDLE
        // =====================
        if (validator.isIdle(
                snake.dx,
                snake.dy
        )) {
            return;
        }

        // =====================
        // GET HEAD
        // =====================
        Point head = snake.body.getFirst();

        // =====================
        // COMPUTE NEW HEAD
        // =====================
        Point newHead =
                validator.computeNewHead(
                        head,
                        snake.dx,
                        snake.dy
                );

        // =====================
        // MOVE
        // =====================
        snake.body.addFirst(newHead);

        snake.body.removeLast(
        );
    }
}