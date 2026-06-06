package startgame;

import eatfood.FoodManager;

import java.awt.*;
import java.util.ArrayList;
import eatfood.ScoreManager;
public class GameEngine {
    // ========================= // GAME CONFIG // =========================
    public final ScoreManager scoreManager = new ScoreManager();
    public int delay = 120;
    // =========================
    // AUDIO
    // =========================
    public boolean soundEnabled = true; // SFX
    public boolean musicEnabled = true; // Background music
    public Color player1HeadColor = Color.GREEN;
    public Color player1BodyColor = new Color(45, 180, 0);
    public Color player2HeadColor = Color.ORANGE;
    public Color player2BodyColor = Color.YELLOW;
    // ========================= // GAME STATE // =========================
    public boolean running = false;
    // ========================= // GAME MODE // =========================
    public boolean multiplayer = false;
    // ========================= // PLAYERS // =========================
    public PlayerSnake player1;
    public PlayerSnake player2;
    // ========================= // FOODS // =========================
    public ArrayList<Point> foods = new ArrayList<>();
    // ========================= // BACKGROUND // =========================
    public String backgroundMusic = "background1";
    // ========================= // FOOD MANAGER // =========================
    private final FoodManager foodManager = new FoodManager();
    // ========================= // INIT GAME // =========================
    public void init() {
        // ===================== // PLAYER 1 // =====================
        player1 = new PlayerSnake(
                "Player 1",
                player1HeadColor,
                player1BodyColor
        );
        player1.body.clear();
        player1.body.add(new Point(5, 5));
        player1.body.add(new Point(4, 5));
        player1.body.add(new Point(3, 5));
        // ===================== // PLAYER 2 // =====================
        if (multiplayer) {
            player2 = new PlayerSnake(
                    "Player 2",
                    player2HeadColor,
                    player2BodyColor
            );
            player2.body.clear();
            player2.body.add(new Point(18, 18));
            player2.body.add(new Point(19, 18));
            player2.body.add(new Point(20, 18));
        } // ===================== // RESET FOOD // =====================//
        foods.clear();
        foods.add(foodManager.spawnFood());
        if (multiplayer) {
            foods.add(foodManager.spawnFood());
        } // ===================== // RESET SCORE // =====================
        scoreManager.initPlayer(player1);
        if(multiplayer){
            scoreManager.initPlayer(player2);
        }
        // ===================== // RESET DIRECTION // =====================
        player1.dx = 0;
        player1.dy = 0;
        if (multiplayer) {
            player2.dx = 0;
            player2.dy = 0;
        }
        running = true;
    }

}
