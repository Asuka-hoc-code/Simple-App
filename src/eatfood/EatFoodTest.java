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
        int oldScore = engine.player1.score;

        EatFoodService service = new EatFoodService();

        boolean result =
                service.handleEat(
                        engine.player1,
                        engine
                );

        assertTrue(result);

        assertEquals(
                oldScore + 1,
                engine.player1.score
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
        int oldScore = engine.player1.score;

        EatFoodService service = new EatFoodService();

        boolean result =
                service.handleEat(
                        engine.player1,
                        engine
                );

        assertFalse(result);

        assertEquals(
                oldScore,
                engine.player1.score
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
                    engine
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
}