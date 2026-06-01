package eatfood;

import startgame.GameEngine;
import startgame.PlayerSnake;

import java.awt.*;

/**
 * =========================================================
 * PHÂN KHU: eatfood
 * CHỨC NĂNG: Xử lý cơ chế ăn thức ăn của rắn
 * =========================================================
 *
 * Lớp này chịu trách nhiệm kiểm tra xem đầu rắn có đang
 * trùng với vị trí của một thức ăn trên bản đồ hay không.
 *
 * Khi rắn ăn được thức ăn:
 * - Tăng chiều dài cơ thể rắn.
 * - Tăng điểm số của người chơi.
 * - Sinh lại thức ăn mới ở vị trí ngẫu nhiên.
 *
 * Đây là một phần quan trọng trong gameplay vì quyết định
 * sự phát triển của rắn và điểm số đạt được trong quá trình chơi.
 */
public class EatFoodService {

    /**
     * Kiểm tra và xử lý sự kiện rắn ăn thức ăn.
     *
     * Quy trình:
     * 1. Lấy vị trí đầu rắn.
     * 2. Duyệt toàn bộ danh sách thức ăn hiện có.
     * 3. Nếu đầu rắn trùng với vị trí thức ăn:
     *      - Tăng chiều dài cơ thể.
     *      - Tăng điểm.
     *      - Sinh thức ăn mới.
     *      - Trả về true.
     * 4. Nếu không ăn được thức ăn nào trả về false.
     *
     * @param snake Đối tượng rắn của người chơi.
     * @param engine Bộ điều khiển trò chơi chứa danh sách thức ăn.
     * @return true nếu rắn đã ăn thức ăn, ngược lại false.
     */
    public boolean handleEat(
            PlayerSnake snake,
            GameEngine engine
    ) {

        // Lấy vị trí đầu rắn (phần tử đầu tiên trong danh sách body)
        Point head = snake.body.get(0);

        // Duyệt qua tất cả thức ăn hiện có
        for (int i = 0; i < engine.foods.size(); i++) {

            Point food = engine.foods.get(i);

            // Kiểm tra đầu rắn có chạm vào thức ăn không
            if (head.equals(food)) {

                // Lấy vị trí đuôi hiện tại
                Point tail =
                        snake.body.get(
                                snake.body.size() - 1
                        );

                // Thêm một đốt mới vào cuối thân rắn
                // giúp rắn dài ra sau khi ăn
                snake.body.add(new Point(tail));

                // Tăng điểm số người chơi
                snake.score++;

                // Sinh lại thức ăn mới tại vị trí ngẫu nhiên
                engine.foods.set(
                        i,
                        new FoodManager().spawnFood()
                );

                return true;
            }
        }

        // Không ăn được thức ăn nào
        return false;
    }
}