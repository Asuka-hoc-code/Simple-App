package eatfood;

import java.awt.*;
import java.util.Random;

/**
 * =========================================================
 * PHÂN KHU: eatfood
 * CHỨC NĂNG: Quản lý việc sinh thức ăn
 * =========================================================
 *
 * Lớp này chịu trách nhiệm tạo ra các vị trí thức ăn mới
 * trên bản đồ trò chơi.
 *
 * Thức ăn được sinh ngẫu nhiên theo hệ tọa độ lưới
 * (grid system) của game để đảm bảo vị trí thức ăn
 * luôn nằm đúng trên các ô mà rắn có thể di chuyển tới.
 *
 * Hiện tại lớp chỉ hỗ trợ sinh thức ăn ngẫu nhiên,
 * nhưng trong tương lai có thể mở rộng để:
 * - Sinh nhiều loại thức ăn.
 * - Sinh thức ăn đặc biệt.
 * - Điều khiển tần suất xuất hiện thức ăn.
 */
public class FoodManager {

    /**
     * Chiều rộng khu vực chơi (pixel).
     */
    private static final int WIDTH = 600;

    /**
     * Chiều cao khu vực chơi (pixel).
     */
    private static final int HEIGHT = 600;

    /**
     * Kích thước một ô trên lưới.
     * Mọi đối tượng trong game đều di chuyển theo đơn vị này.
     */
    private static final int UNIT_SIZE = 25;

    /**
     * Đối tượng dùng để sinh số ngẫu nhiên.
     */
    Random random = new Random();

    /**
     * Sinh một vị trí thức ăn ngẫu nhiên trên bản đồ.
     *
     * Tọa độ được tạo dựa trên số lượng ô trong lưới:
     * - x thuộc [0, WIDTH / UNIT_SIZE)
     * - y thuộc [0, HEIGHT / UNIT_SIZE)
     *
     * @return Vị trí mới của thức ăn dưới dạng Point.
     */
    public Point spawnFood() {

        // Sinh ngẫu nhiên cột trên lưới
        int x = random.nextInt(WIDTH / UNIT_SIZE);

        // Sinh ngẫu nhiên hàng trên lưới
        int y = random.nextInt(HEIGHT / UNIT_SIZE);

        // Trả về tọa độ thức ăn mới
        return new Point(x, y);
    }
}