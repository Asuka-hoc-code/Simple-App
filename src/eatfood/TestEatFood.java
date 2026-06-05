package eatfood;

import startgame.GameEngine;

import java.awt.Point;

/**
 * =========================================================
 * TEST CLASS: TestEatFood
 * =========================================================
 *
 * Lớp dùng để kiểm thử thủ công (manual test)
 * cho các chức năng thuộc package eatfood:
 *
 * 1. FoodManager.spawnFood()
 *      - Sinh vị trí thức ăn ngẫu nhiên.
 *
 * 2. EatFoodService.handleEat()
 *      - Kiểm tra cơ chế rắn ăn thức ăn.
 *      - Tăng điểm.
 *      - Tăng chiều dài thân.
 *      - Sinh lại thức ăn mới.
 *
 * Chạy hàm main() để xem kết quả trên console.
 */
public class TestEatFood {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("TEST FOOD MANAGER");
        System.out.println("=================================");

        testSpawnFood();

        System.out.println("\n=================================");
        System.out.println("TEST EAT FOOD SERVICE");
        System.out.println("=================================");

        testHandleEat();
    }

    /**
     * ---------------------------------------------------------
     * TEST 1: FoodManager.spawnFood()
     * ---------------------------------------------------------
     *
     * Mục đích:
     * - Kiểm tra việc sinh thức ăn.
     * - Kiểm tra tọa độ có nằm trong lưới game.
     *
     * Kết quả mong đợi:
     * - Trả về các Point khác nhau.
     * - Không phát sinh lỗi.
     */
    private static void testSpawnFood() {

        FoodManager foodManager = new FoodManager();

        for (int i = 1; i <= 5; i++) {

            Point food = foodManager.spawnFood();

            System.out.println(
                    STR."Food \{i} -> (\{food.x}, \{food.y})"
            );
        }
    }

    /**
     * ---------------------------------------------------------
     * TEST 2: EatFoodService.handleEat()
     * ---------------------------------------------------------
     *
     * Mục đích:
     * - Đặt thức ăn đúng vị trí đầu rắn.
     * - Kiểm tra rắn có ăn được hay không.
     * - Kiểm tra điểm số có tăng hay không.
     * - Kiểm tra chiều dài thân có tăng hay không.
     *
     * Kết quả mong đợi:
     * - Result = true
     * - Score tăng từ 0 lên 1
     * - Length tăng từ 3 lên 4
     * - Food được sinh lại vị trí mới
     */
    private static void testHandleEat() {

        // Khởi tạo game
        GameEngine engine = new GameEngine();
        engine.init();

        // Đặt thức ăn ngay đầu rắn
        engine.foods.clear();
        engine.foods.add(new Point(5, 5));

        System.out.println("----- BEFORE EAT -----");
        System.out.println(STR."Score  : \{engine.scoreManager.getScore(engine.player1)}");
        System.out.println(STR."Length : \{engine.player1.body.size()}");
        System.out.println(STR."Food   : \{engine.foods.get(0)}");

        EatFoodService service = new EatFoodService();

        boolean result =
                service.handleEat(
                        engine.player1,
                        engine, engine.scoreManager
                );

        System.out.println("\n----- AFTER EAT -----");
        System.out.println(STR."Result : \{result}");
        System.out.println(STR."Score  : \{engine.scoreManager.getScore(engine.player1)}");
        System.out.println(STR."Length : \{engine.player1.body.size()}");
        System.out.println(STR."Food   : \{engine.foods.get(0)}");
    }
}