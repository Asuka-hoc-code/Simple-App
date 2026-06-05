package eatfood;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import startgame.GameEngine;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.*;

/**
 * =========================================================
 * TEST CLASS: EatFoodTest
 * =========================================================
 *
 * Chức năng kiểm thử:
 *
 * 1. FoodManager.spawnFood()
 *      - Sinh thức ăn hợp lệ.
 *
 * 2. EatFoodService.handleEat()
 *      - Rắn ăn được thức ăn.
 *      - Rắn không ăn được thức ăn.
 *
 * 3. Performance Test
 *      - Đo hiệu suất sinh thức ăn.
 *      - Đo hiệu suất xử lý ăn thức ăn.
 *
 * Framework:
 *      JUnit 5
 *
 * Cách import thư viện JUnit trong Intellij
 *      1. Chọn vô các @ (@Test...)
 *      2. ALt + Enter
 *      3. Add JUnit 5.x.x to classpath...
 *      4. Chọn nơi lưu
 *    -> Thư viện JUnit sẽ tự động tải về
 * Sau khi tải xong hãy restart lại Intellij
 *
 * =========================================================
 */
class EatFoodTest {

    /**
     * =====================================================
     * TEST FOOD MANAGER
     * =====================================================
     *
     * Kiểm tra:
     * - spawnFood() trả về đối tượng Point.
     * - Tọa độ luôn nằm trong phạm vi map.
     */
    @Test
    @DisplayName("FoodManager - Spawn food hợp lệ")
    void testSpawnFood() {

        FoodManager manager = new FoodManager();

        Point food = manager.spawnFood();

        assertNotNull(food);

        // WIDTH = 600, UNIT_SIZE = 25
        // => grid = 24 ô
        assertTrue(food.x >= 0);
        assertTrue(food.x < 24);

        assertTrue(food.y >= 0);
        assertTrue(food.y < 24);
    }

    /**
     * =====================================================
     * TEST EAT FOOD SUCCESS
     * =====================================================
     *
     * Kiểm tra trường hợp:
     * - Đầu rắn trùng vị trí thức ăn.
     *
     * Kết quả mong đợi:
     * - Trả về true.
     * - Điểm tăng 1.
     * - Thân tăng thêm 1 đốt.
     */
    @Test
    @DisplayName("EatFoodService - Ăn thức ăn thành công")
    void testHandleEatSuccess() {

        GameEngine engine = new GameEngine();
        engine.init();

        engine.foods.clear();
        engine.foods.add(new Point(5, 5));

        int oldLength = engine.player1.body.size();
        int oldScore = engine.scoreManager.getScore(engine.player1);

        EatFoodService service = new EatFoodService();

        boolean result =
                service.handleEat(
                        engine.player1,
                        engine, engine.scoreManager
                );

        assertTrue(result);

        assertEquals(
                oldScore + 1,
                engine.scoreManager.getScore(engine.player1)
        );

        assertEquals(
                oldLength + 1,
                engine.player1.body.size()
        );
    }

    /**
     * =====================================================
     * TEST EAT FOOD FAIL
     * =====================================================
     *
     * Kiểm tra trường hợp:
     * - Đầu rắn không chạm thức ăn.
     *
     * Kết quả mong đợi:
     * - Trả về false.
     * - Điểm giữ nguyên.
     * - Độ dài giữ nguyên.
     */
    @Test
    @DisplayName("EatFoodService - Không ăn được thức ăn")
    void testHandleEatFail() {

        GameEngine engine = new GameEngine();
        engine.init();

        engine.foods.clear();
        engine.foods.add(new Point(20, 20));

        int oldLength = engine.player1.body.size();
        int oldScore = engine.scoreManager.getScore(engine.player1);

        EatFoodService service = new EatFoodService();

        boolean result =
                service.handleEat(
                        engine.player1,
                        engine,
                        engine.scoreManager
                );

        assertFalse(result);

        assertEquals(
                oldScore,
                engine.scoreManager.getScore(engine.player1)
        );

        assertEquals(
                oldLength,
                engine.player1.body.size()
        );
    }

    /**
     * =====================================================
     * PERFORMANCE TEST:
     * FoodManager.spawnFood()
     * =====================================================
     *
     * Sinh 100.000 thức ăn liên tiếp.
     *
     * Mục tiêu:
     * - Không phát sinh lỗi.
     * - Thời gian thực thi đủ nhanh.
     */
    @Test
    @DisplayName("Performance - Spawn 100000 foods")
    void testSpawnFoodPerformance() {

        FoodManager manager = new FoodManager();

        long start = System.nanoTime();

        for (int i = 0; i < 100_000; i++) {
            manager.spawnFood();
        }

        long end = System.nanoTime();

        long elapsedMs =
                (end - start) / 1_000_000;

        System.out.println(
                STR."Spawn 100000 foods = \{elapsedMs} ms"
        );

        // Giới hạn tương đối
        assertTrue(elapsedMs < 1000);
    }

    /**
     * =====================================================
     * PERFORMANCE TEST:
     * EatFoodService.handleEat()
     * =====================================================
     *
     * Thực hiện xử lý ăn thức ăn 100.000 lần.
     *
     * Mục tiêu:
     * - Đánh giá tốc độ xử lý logic gameplay.
     */
    @Test
    @DisplayName("Performance - Handle eat 100000 times")
    void testHandleEatPerformance() {

        EatFoodService service =
                new EatFoodService();

        long start = System.nanoTime();

        for (int i = 0; i < 100_000; i++) {

            GameEngine engine =
                    new GameEngine();

            engine.init();

            engine.foods.clear();

            engine.foods.add(
                    new Point(5, 5)
            );

            service.handleEat(
                    engine.player1,
                    engine, engine.scoreManager
            );
        }

        long end = System.nanoTime();

        long elapsedMs =
                (end - start) / 1_000_000;

        System.out.println(
                STR."HandleEat 100000 times = \{elapsedMs} ms"
        );

        assertTrue(elapsedMs < 5000);
    }
    /**
     * =====================================================
     * TEST SCORE MANAGER:
     * Init Player
     * =====================================================
     *
     * Kiểm tra:
     * - Người chơi mới được khởi tạo điểm = 0.
     */
    @Test
    @DisplayName("ScoreManager - Khởi tạo điểm số")
    void testInitPlayerScore() {

        GameEngine engine = new GameEngine();
        engine.init();

        assertEquals(
                0,
                engine.scoreManager.getScore(
                        engine.player1
                )
        );
    }
    /**
     * =====================================================
     * TEST SCORE MANAGER:
     * Add Score
     * =====================================================
     *
     * Kiểm tra:
     * - addScore() tăng đúng 1 điểm.
     */
    @Test
    @DisplayName("ScoreManager - Tăng điểm")
    void testAddScore() {

        GameEngine engine = new GameEngine();
        engine.init();

        engine.scoreManager.addScore(
                engine.player1
        );

        assertEquals(
                1,
                engine.scoreManager.getScore(
                        engine.player1
                )
        );
    }
    /**
     * =====================================================
     * TEST SCORE MANAGER:
     * Add Score Multiple Times
     * =====================================================
     *
     * Kiểm tra:
     * - Điểm được cộng dồn chính xác.
     */
    @Test
    @DisplayName("ScoreManager - Cộng dồn điểm")
    void testAddScoreMultipleTimes() {

        GameEngine engine = new GameEngine();
        engine.init();

        for (int i = 0; i < 10; i++) {

            engine.scoreManager.addScore(
                    engine.player1
            );
        }

        assertEquals(
                10,
                engine.scoreManager.getScore(
                        engine.player1
                )
        );
    }
    /**
     * =====================================================
     * TEST SCORE MANAGER:
     * Reset Score
     * =====================================================
     *
     * Kiểm tra:
     * - reset() đưa điểm về 0.
     */
    @Test
    @DisplayName("ScoreManager - Reset điểm")
    void testResetScore() {

        GameEngine engine = new GameEngine();
        engine.init();

        engine.scoreManager.addScore(
                engine.player1
        );

        engine.scoreManager.addScore(
                engine.player1
        );

        engine.scoreManager.reset(
                engine.player1
        );

        assertEquals(
                0,
                engine.scoreManager.getScore(
                        engine.player1
                )
        );
    }
    /**
     * =====================================================
     * TEST SCORE MANAGER:
     * Multiplayer Score
     * =====================================================
     *
     * Kiểm tra:
     * - Điểm số 2 người chơi độc lập.
     */
    @Test
    @DisplayName("ScoreManager - Điểm số Multiplayer độc lập")
    void testMultiplayerScore() {

        GameEngine engine = new GameEngine();

        engine.multiplayer = true;
        engine.init();

        engine.scoreManager.addScore(
                engine.player1
        );

        engine.scoreManager.addScore(
                engine.player1
        );

        engine.scoreManager.addScore(
                engine.player2
        );

        assertEquals(
                2,
                engine.scoreManager.getScore(
                        engine.player1
                )
        );

        assertEquals(
                1,
                engine.scoreManager.getScore(
                        engine.player2
                )
        );
    }
    /**
     * =====================================================
     * PERFORMANCE TEST:
     * ScoreManager.addScore()
     * =====================================================
     *
     * Thực hiện cộng điểm 100.000 lần.
     */
    @Test
    @DisplayName("Performance - Add score 100000 times")
    void testAddScorePerformance() {

        GameEngine engine = new GameEngine();
        engine.init();

        long start = System.nanoTime();

        for (int i = 0; i < 100_000; i++) {

            engine.scoreManager.addScore(
                    engine.player1
            );
        }

        long end = System.nanoTime();

        long elapsedMs =
                (end - start) / 1_000_000;

        System.out.println(
                STR."AddScore 100000 times = \{elapsedMs} ms"
        );

        assertTrue(elapsedMs < 1000);
    }
}