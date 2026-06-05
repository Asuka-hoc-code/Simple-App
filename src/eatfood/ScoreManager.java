package eatfood;

import startgame.PlayerSnake;

import java.util.HashMap;
import java.util.Map;

/**
 * =========================================================
 * PHÂN KHU: eatfood
     * CHỨC NĂNG: Quản lý điểm số người chơi
 * =========================================================
 *
 * Lớp này chịu trách nhiệm lưu trữ và quản lý điểm số
 * của từng người chơi trong trò chơi.
 *
 * Thay vì lưu điểm trực tiếp bên trong PlayerSnake,
 * ScoreManager sử dụng cấu trúc Map để liên kết:
 *
 *      PlayerSnake -> Điểm số
 *
 * Điều này giúp:
 * - Tách biệt trách nhiệm quản lý điểm khỏi lớp PlayerSnake.
 * - Dễ bảo trì và mở rộng hệ thống.
 * - Tuân thủ nguyên tắc Single Responsibility Principle (SRP).
 *
 * Hiện tại lớp hỗ trợ:
 * - Khởi tạo điểm cho người chơi.
 * - Tăng điểm khi ăn thức ăn.
 * - Lấy điểm hiện tại.
 * - Đặt lại điểm về 0 khi bắt đầu ván mới.
 */
public class ScoreManager {

    /**
     * Lưu trữ điểm số của từng người chơi.
     *
     * Key   : Đối tượng PlayerSnake.
     * Value : Điểm hiện tại của người chơi.
     */
    private final Map<PlayerSnake, Integer> scores =
            new HashMap<>();

    /**
     * Khởi tạo điểm ban đầu cho người chơi.
     *
     * Khi bắt đầu trò chơi hoặc tạo người chơi mới,
     * điểm sẽ được đặt về 0.
     *
     * @param snake Người chơi cần khởi tạo điểm.
     */
    public void initPlayer(PlayerSnake snake) {

        scores.put(
                snake,
                0
        );
    }

    /**
     * Tăng điểm cho người chơi thêm 1 đơn vị.
     *
     * Thường được gọi khi rắn ăn được một thức ăn.
     *
     * @param snake Người chơi cần tăng điểm.
     */
    public void addScore(PlayerSnake snake) {

        scores.put(
                snake,
                getScore(snake) + 1
        );
    }

    /**
     * Lấy điểm hiện tại của người chơi.
     *
     * Nếu người chơi chưa được khởi tạo trong hệ thống,
     * phương thức sẽ trả về 0 để tránh lỗi NullPointerException.
     *
     * @param snake Người chơi cần lấy điểm.
     * @return Điểm hiện tại.
     */
    public int getScore(PlayerSnake snake) {

        return scores.getOrDefault(
                snake,
                0
        );
    }

    /**
     * Đặt lại điểm của người chơi về 0.
     *
     * Thường được sử dụng khi người chơi
     * bắt đầu một ván mới hoặc restart game.
     *
     * @param snake Người chơi cần reset điểm.
     */
    public void reset(PlayerSnake snake) {

        scores.put(
                snake,
                0
        );
    }
}