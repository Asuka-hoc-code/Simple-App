package startgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import move.*;
import eatfood.*;
import handlecollision.*;
import restart.*;

public class SnakeGameApp extends JPanel implements ActionListener {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final int UNIT_SIZE = 25;

    private static final Font SCORE_FONT =
            new Font("Arial", Font.BOLD, 20);

    private static final Font GAME_OVER_FONT =
            new Font("Arial", Font.BOLD, 40);

    private static final Font INFO_FONT =
            new Font("Arial", Font.PLAIN, 20);

    private static final Color SNAKE_BODY_COLOR =
            new Color(45, 180, 0);

    private final GameEngine engine;

    private final JFrame parentFrame;

    private final GameController gameController;

    private final BackToMenuService backToMenuService;

    private final MoveController moveController;

    private final EatFoodService eatFoodService;

    private final CollisionService collisionService;

    private final RestartService restartService;

    private final DirectionManager directionManager;

    private final FoodManager foodManager;

    private final Timer timer;

    public SnakeGameApp(
            GameEngine engine,
            JFrame parentFrame
    ) {

        this.engine = engine;

        this.parentFrame = parentFrame;

        setPreferredSize(
                new Dimension(WIDTH, HEIGHT)
        );

        setBackground(Color.BLACK);

        setFocusable(true);

        gameController =
                new GameController(engine);

        backToMenuService =
                new BackToMenuService();

        moveController =
                new MoveController(engine);

        eatFoodService =
                new EatFoodService();

        collisionService =
                new CollisionService();

        restartService =
                new RestartService();

        directionManager =
                new DirectionManager();

        foodManager =
                new FoodManager();

        engine.food =
                foodManager.spawnFood();

        timer =
                new Timer(engine.delay, this);

        setupKeys();

        updateGameSpeed();

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        updateGame();

        repaint();
    }

    private void updateGame() {

        if (!engine.running) {
            return;
        }

        moveController.moveSnake();

        eatFoodService.handleEat(engine);

        handleFoodState();

        collisionService.check(engine);

        if (!engine.running) {

            timer.stop();
        }
    }

    private void handleFoodState() {

        if (engine.foodEaten) {

            engine.food =
                    foodManager.spawnFood();

            engine.foodEaten = false;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (engine.running) {

            drawFood(g);

            drawSnake(g);

            drawScore(g);

        } else {

            drawGameOver(g);
        }
    }

    private void drawFood(Graphics g) {

        g.setColor(Color.RED);

        g.fillOval(
                engine.food.x * UNIT_SIZE,
                engine.food.y * UNIT_SIZE,
                UNIT_SIZE,
                UNIT_SIZE
        );
    }

    private void drawSnake(Graphics g) {

        for (int i = 0; i < engine.snake.size(); i++) {

            Point p =
                    engine.snake.get(i);

            setSnakeSkinColor(g, i);

            g.fillRect(
                    p.x * UNIT_SIZE,
                    p.y * UNIT_SIZE,
                    UNIT_SIZE,
                    UNIT_SIZE
            );
        }
    }

    private void setSnakeSkinColor(
            Graphics g,
            int index
    ) {

        switch (engine.skin) {

            case "Blue Snake":

                g.setColor(
                        index == 0
                                ? Color.CYAN
                                : Color.BLUE
                );
                break;

            case "White Snake":

                g.setColor(
                        index == 0
                                ? Color.WHITE
                                : Color.LIGHT_GRAY
                );
                break;

            default:

                g.setColor(
                        index == 0
                                ? Color.GREEN
                                : SNAKE_BODY_COLOR
                );
        }
    }

    private void drawScore(Graphics g) {

        g.setColor(Color.WHITE);

        g.setFont(SCORE_FONT);

        g.drawString(
                "Score: " + engine.score,
                10,
                20
        );
    }

    private void drawGameOver(Graphics g) {

        g.setColor(Color.RED);

        g.setFont(GAME_OVER_FONT);

        g.drawString(
                "GAME OVER",
                WIDTH / 2 - 125,
                HEIGHT / 2
        );

        g.setFont(INFO_FONT);

        g.drawString(
                "Score: " + engine.score,
                WIDTH / 2 - 40,
                HEIGHT / 2 + 40
        );

        g.drawString(
                "Press R to Restart",
                WIDTH / 2 - 90,
                HEIGHT / 2 + 80
        );

        g.drawString(
                "Backspace: Back To Menu",
                WIDTH / 2 - 120,
                HEIGHT / 2 + 100
        );
    }

    private void setupKeys() {

        InputMap im =
                getInputMap(
                        WHEN_IN_FOCUSED_WINDOW
                );

        ActionMap am =
                getActionMap();

        im.put(
                KeyStroke.getKeyStroke("W"),
                "up"
        );

        im.put(
                KeyStroke.getKeyStroke("S"),
                "down"
        );

        im.put(
                KeyStroke.getKeyStroke("A"),
                "left"
        );

        im.put(
                KeyStroke.getKeyStroke("D"),
                "right"
        );

        im.put(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_BACK_SPACE,
                        0
                ),
                "backMenu"
        );

        im.put(
                KeyStroke.getKeyStroke("R"),
                "restart"
        );

        am.put("up", new AbstractAction() {

            @Override
            public void actionPerformed(
                    ActionEvent e
            ) {

                changeDirection(
                        0,
                        -1
                );
            }
        });

        am.put("down", new AbstractAction() {

            @Override
            public void actionPerformed(
                    ActionEvent e
            ) {

                changeDirection(
                        0,
                        1
                );
            }
        });

        am.put("left", new AbstractAction() {

            @Override
            public void actionPerformed(
                    ActionEvent e
            ) {

                changeDirection(
                        -1,
                        0
                );
            }
        });

        am.put("right", new AbstractAction() {

            @Override
            public void actionPerformed(
                    ActionEvent e
            ) {

                changeDirection(
                        1,
                        0
                );
            }
        });

        am.put("restart", new AbstractAction() {

            @Override
            public void actionPerformed(
                    ActionEvent e
            ) {

                restartGame();
            }
        });

        am.put("backMenu", new AbstractAction() {

            @Override
            public void actionPerformed(
                    ActionEvent e
            ) {

                timer.stop();

                backToMenuService.backToMenu(
                        parentFrame,
                        gameController
                );
            }
        });
    }

    private void changeDirection(
            int newDx,
            int newDy
    ) {

        if (directionManager.isValidChange(
                engine.dx,
                engine.dy,
                newDx,
                newDy
        )) {

            engine.dx = newDx;

            engine.dy = newDy;
        }
    }

    private void restartGame() {

        restartService.restart(engine);

        engine.food =
                foodManager.spawnFood();

        timer.restart();

        repaint();
    }

    public void updateGameSpeed() {
        timer.setDelay(engine.delay);
        timer.setInitialDelay(
                engine.delay
        );
    }
    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(() -> {

            GameEngine engine =
                    new GameEngine();

            GameController controller =
                    new GameController(engine);

            MenuUI menu =
                    new MenuUI(controller);

            menu.setVisible(true);
        });
    }
}
