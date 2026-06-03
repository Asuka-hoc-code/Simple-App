package startgame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    public int delay = 120;
    public boolean soundEnabled = true;
    public String skin = "Classic Green";

    public boolean running = false;
    public boolean foodEaten = false;
    public int score = 0;

    public int dx = 0;
    public int dy = 0;

    
    public final List<Point> snake = new ArrayList<>();
    public Point food;

    
    public void init() {
        snake.clear();
        snake.add(new Point(5, 5));
        snake.add(new Point(4, 5));
        snake.add(new Point(3, 5));
        dx = 0;
        dy = 0;
        score = 0;
        foodEaten = false;
        running = true;
    }
}
