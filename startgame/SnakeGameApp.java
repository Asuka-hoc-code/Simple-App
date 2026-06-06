package startgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import configuregame.ConfigureGameUI;
import move.*;
import eatfood.*;
import handlecollision.*;
import restart.*;
import sound.SoundManager;

public class SnakeGameApp extends JPanel implements ActionListener {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final int UNIT_SIZE = 25;

    
    // ENGINE
    
    private final GameEngine engine;
    private boolean gameOverPlayed = false;
    
    // CONTROLLERS / SERVICES
    

    private final JFrame parentFrame;

    private final BackToMenuService backToMenuService;

    private final MoveController moveController;

    private final EatFoodService eatFoodService;

    private final CollisionService collisionService;

    private final RestartService restartService;

    private final DirectionManager directionManager;

    private final SoundManager soundManager;
    
    // TIMER
    
    private final Timer timer;

    
    // CONSTRUCTOR
    
    public SnakeGameApp(
            GameEngine engine,
            JFrame parentFrame
    ) {

        this.engine = engine;

        this.parentFrame = parentFrame;

        setPreferredSize(
                new Dimension(WIDTH, HEIGHT)
        );

        setBackground(new Color(23, 22, 22));

        setFocusable(true);

        // =====================
        // INIT SERVICES
        // =====================


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
        soundManager =
                new SoundManager();
        // =====================
        // TIMER
        // =====================
        timer = new Timer(
                engine.delay,
                this
        );

        setupKeys();

        updateGameSpeed();
        if (engine.musicEnabled) {
            String musicPath = getMusicPath(engine.backgroundMusic);
            soundManager.playBackgroundMusic(musicPath);
        }
        timer.start();
    }

    private String getMusicPath(String music) {

        return switch (music) {
            case "Background 1" -> "/sound/background1.wav";
            case "Background 2" -> "/sound/background2.wav";
            case "Background 3" -> "/sound/background3.wav";
            default -> "/sound/background1.wav";
        };
    }

    
    // GAME LOOP
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (engine.running) {

            // =====================
            // MOVE PLAYER 1
            // =====================
            moveController.moveSnake(
                    engine.player1
            );

            boolean ateFoodP1 =
                    eatFoodService.handleEat(
                            engine.player1,
                            engine,
                            engine.scoreManager
                    );
            if (ateFoodP1 && engine.soundEnabled) {
                soundManager.playSound("/sound/eatFoodSFX.wav");
            }

            // =====================
            // MOVE PLAYER 2
            // =====================
            if (engine.multiplayer) {

                moveController.moveSnake(
                        engine.player2
                );

                boolean ateFoodP2 = eatFoodService.handleEat(
                        engine.player2,
                        engine, engine.scoreManager
                );
                if (ateFoodP2 && engine.soundEnabled) {
                    soundManager.playSound("/sound/eatfood.wav");
                }
            }

            // =====================
            // COLLISION
            // =====================
            collisionService.check(engine);

            if (!engine.running && !gameOverPlayed) {

                gameOverPlayed = true;

                timer.stop();

                if (engine.soundEnabled) {

                    soundManager.stopBackgroundMusic();

                    soundManager.playSound(
                            "/sound/gameoverSFX.wav"
                    );
                }
            }
        }

        repaint();
    }

    
    // RENDER
    
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (engine.running) {

            drawFood(g);

            drawSnake(
                    g,
                    engine.player1
            );

            if (engine.multiplayer) {

                drawSnake(
                        g,
                        engine.player2
                );
            }

            drawScore(g);

        } else {

            drawGameOver(g);
        }
    }

    
    // DRAW FOOD
    
    private void drawFood(Graphics g) {

        g.setColor(Color.RED);

        for (Point food : engine.foods) {

            g.fillOval(
                    food.x * UNIT_SIZE,
                    food.y * UNIT_SIZE,
                    UNIT_SIZE,
                    UNIT_SIZE
            );
        }
    }

    
    // DRAW SNAKE
    
    private void drawSnake(
            Graphics g,
            PlayerSnake snake
    ) {

        for (int i = 0; i < snake.body.size(); i++) {

            if (i == 0) {

                g.setColor(
                        snake.headColor
                );

            } else {

                g.setColor(
                        snake.bodyColor
                );
            }

            Point p = snake.body.get(i);

            g.fillRect(
                    p.x * UNIT_SIZE,
                    p.y * UNIT_SIZE,
                    UNIT_SIZE,
                    UNIT_SIZE
            );
        }
    }

    
    // SCORE
    
    private void drawScore(Graphics g) {

        g.setColor(Color.WHITE);

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        if (!engine.multiplayer) {

            g.drawString(
                    STR."Score: \{engine.scoreManager.getScore(
                            engine.player1
                    )}",
                    10,
                    20
            );

        } else {

            g.drawString(
                    STR."P1: \{engine.scoreManager.getScore(engine.player1)}",
                    10,
                    20
            );

            g.drawString(
                    STR."P2: \{engine.scoreManager.getScore(engine.player2)}",
                    500,
                    20
            );
        }
    }

    
    // GAME OVER
    
    private void drawGameOver(Graphics g) {

        g.setColor(Color.RED);

        if (!engine.multiplayer) {
            g.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            40
                    )
            );
            g.drawString(
                    "GAME OVER",
                    WIDTH / 2 - 125,
                    HEIGHT / 2
            );
            g.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            20
                    )
            );
            g.drawString(
                    STR."Score: \{engine.scoreManager.getScore(engine.player1)}",
                    WIDTH / 2 - 50,
                    HEIGHT / 2 + 50
            );

        } else {
            g.setColor(new Color(41, 164, 10));
            g.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            40
                    )
            );
            String winner;
            if (engine.scoreManager.getScore(engine.player1) >
                    engine.scoreManager.getScore(engine.player2)) {

                winner = "PLAYER 1 WIN!";

            } else if (
                    engine.scoreManager.getScore(engine.player2) >
                            engine.scoreManager.getScore(engine.player1)
            ) {

                winner = "PLAYER 2 WIN!";

            } else {

                winner = "DRAW!";
            }
            if("DRAW!".equals(winner)) {
                g.drawString(
                        winner,
                        WIDTH / 2 - 50,
                        HEIGHT / 2 + 50
                );
            }else{
                g.drawString(
                        winner,
                        WIDTH / 2 - 130,
                        HEIGHT / 2 + 50
                );
            }

            g.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            20
                    )
            );

            g.drawString(
                    STR."Player 1: \{engine.scoreManager.getScore(engine.player1)}",
                    WIDTH / 2 - 120,
                    HEIGHT / 2 + 80
            );

            g.drawString(
                    STR."Player 2: \{engine.scoreManager.getScore(engine.player2)}",
                    WIDTH / 2 - 120,
                    HEIGHT / 2 + 110
            );
        }

        g.drawString(
                "Press R to Restart",
                WIDTH / 2 - 90,
                HEIGHT / 2 + 150
        );

        g.drawString(
                "Backspace: Back To Menu",
                WIDTH / 2 - 120,
                HEIGHT / 2 + 180
        );
    }

    
    // INPUT
    
    private void setupKeys() {

        InputMap im =
                getInputMap(
                        WHEN_IN_FOCUSED_WINDOW
                );

        ActionMap am =
                getActionMap();

        // =====================
        // PLAYER 1
        // =====================
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

        // =====================
        // PLAYER 2
        // =====================
        im.put(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_UP,
                        0
                ),
                "p2up"
        );

        im.put(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_DOWN,
                        0
                ),
                "p2down"
        );

        im.put(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_LEFT,
                        0
                ),
                "p2left"
        );

        im.put(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_RIGHT,
                        0
                ),
                "p2right"
        );

        // =====================
        // SYSTEM
        // =====================
        im.put(
                KeyStroke.getKeyStroke("R"),
                "restart"
        );

        im.put(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_BACK_SPACE,
                        0
                ),
                "backMenu"
        );

        // =====================
        // PLAYER 1 ACTIONS
        // =====================
        am.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (directionManager.isValidChange(
                        engine.player1.dx,
                        engine.player1.dy,
                        0,
                        -1
                )) {

                    engine.player1.dx = 0;
                    engine.player1.dy = -1;
                }
            }
        });

        am.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (directionManager.isValidChange(
                        engine.player1.dx,
                        engine.player1.dy,
                        0,
                        1
                )) {

                    engine.player1.dx = 0;
                    engine.player1.dy = 1;
                }
            }
        });

        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (directionManager.isValidChange(
                        engine.player1.dx,
                        engine.player1.dy,
                        -1,
                        0
                )) {

                    engine.player1.dx = -1;
                    engine.player1.dy = 0;
                }
            }
        });

        am.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (directionManager.isValidChange(
                        engine.player1.dx,
                        engine.player1.dy,
                        1,
                        0
                )) {

                    engine.player1.dx = 1;
                    engine.player1.dy = 0;
                }
            }
        });

        // =====================
        // PLAYER 2 ACTIONS
        // =====================
        am.put("p2up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!engine.multiplayer) {
                    return;
                }

                if (directionManager.isValidChange(
                        engine.player2.dx,
                        engine.player2.dy,
                        0,
                        -1
                )) {

                    engine.player2.dx = 0;
                    engine.player2.dy = -1;
                }
            }
        });

        am.put("p2down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!engine.multiplayer) {
                    return;
                }

                if (directionManager.isValidChange(
                        engine.player2.dx,
                        engine.player2.dy,
                        0,
                        1
                )) {

                    engine.player2.dx = 0;
                    engine.player2.dy = 1;
                }
            }
        });

        am.put("p2left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!engine.multiplayer) {
                    return;
                }

                if (directionManager.isValidChange(
                        engine.player2.dx,
                        engine.player2.dy,
                        -1,
                        0
                )) {

                    engine.player2.dx = -1;
                    engine.player2.dy = 0;
                }
            }
        });

        am.put("p2right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!engine.multiplayer) {
                    return;
                }

                if (directionManager.isValidChange(
                        engine.player2.dx,
                        engine.player2.dy,
                        1,
                        0
                )) {

                    engine.player2.dx = 1;
                    engine.player2.dy = 0;
                }
            }
        });

        // =====================
        // RESTART
        // =====================
        am.put("restart", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                restartService.restart(engine);
                if (engine.musicEnabled) {

                    soundManager.playBackgroundMusic(
                            getMusicPath(
                                    engine.backgroundMusic
                            )
                    );
                }
                timer.start();
                gameOverPlayed = false;
                repaint();
            }
        });

        // =====================
        // BACK TO MENU
        // =====================
        am.put("backMenu", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                timer.stop();
                soundManager.stopBackgroundMusic();
                backToMenuService.backToMenu(
                        parentFrame
                );
            }
        });
    }

    
    // GAME SPEED
    
    public void updateGameSpeed() {

        timer.setDelay(engine.delay);

        timer.setInitialDelay(engine.delay);
    }

    
    // MAIN
    
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            ConfigureGameUI menu = new ConfigureGameUI();
            menu.setVisible(true);
        });
    }
}

