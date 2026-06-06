package move;

import startgame.GameEngine;

import java.awt.Point;

/*
public class MoveController {

    private final GameEngine engine;

    public MoveController(GameEngine engine) {
        this.engine = engine;
    }

    public void moveSnake() {

        if (engine.getDx() == 0
                && engine.getDy() == 0) {
            return;
        }

        Point head =
                engine.getSnake().getFirst();

        Point newHead =
                new Point(
                        head.x + engine.getDx(),
                        head.y + engine.getDy()
                );

        engine.getSnake().addFirst(newHead);
        engine.getSnake().removeLast();
    }
}
*/
