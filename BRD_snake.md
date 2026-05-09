# 🐍 BUSINESS REQUIREMENTS DOCUMENT (BRD)
## Snake Game Desktop Application (Java Swing)

---

## 1. 📌 Executive Summary

Snake Game là một ứng dụng desktop được phát triển bằng Java Swing nhằm:
- Cung cấp trải nghiệm giải trí đơn giản
- Minh họa các khái niệm lập trình GUI, game loop, event handling và MVC
- Là nền tảng mở rộng cho các tính năng game nâng cao

Hệ thống hướng tới người dùng cá nhân, sinh viên và người học lập trình Java.

Phiên bản nâng cấp của game hỗ trợ thêm(so với snake game thông thường):
- Pause game
- Chọn độ khó
- Leaderboard
- Lưu điểm
- Sound effect
- Skin/theme
- Tăng tốc độ theo thời gian
- Wall mode
- Obstacle mode

---

## 2. 🎯 Business Objectives

| ID | Objective | Success Metric |
|----|----------|---------------|
| BO1 | Xây dựng game Snake hoạt động ổn định | Không crash sau 1000 lượt chơi |
| BO2 | Trải nghiệm mượt | FPS ≥ 50 |
| BO3 | Dễ sử dụng | User có thể chơi mà không cần hướng dẫn |
| BO4 | Dễ mở rộng | Thêm feature mới < 2h |
| BO5 | Tăng trải nghiệm gameplay | Hỗ trợ nhiều chế độ chơi |
| BO6 | Tăng khả năng tương tác người dùng | Có leaderboard và save score |

---

## 3. 📦 Scope Definition

### 3.1 In Scope
- Game Snake 2D chạy offline
- Điều khiển bằng bàn phím
- Tính điểm
- Collision detection
- Restart game
- Pause game
- Chọn độ khó
- Save score
- Leaderboard
- Sound effect
- Skin/theme
- Dynamic speed
- Wall mode
- Obstacle mode

### 3.2 Out of Scope
- Multiplayer online
- Cloud storage
- AI opponent
- OpenGL graphics
- Mobile version

---

## 4. 👥 Stakeholders

| Stakeholder | Interest | Responsibility |
|------------|---------|---------------|
| End User | Chơi game | Sử dụng |
| Developer | Xây dựng hệ thống | Implement & maintain |
| Tester | Kiểm thử | Report bug |
| Instructor | Đánh giá | Review project |

---

## 5. 🎮 Product Overview

### 5.1 Game Concept
Người chơi điều khiển snake di chuyển trong không gian 2D để ăn food và tăng điểm, đồng thời tránh va chạm với tường, vật cản hoặc chính thân của snake.

### 5.2 Core Mechanics
- Grid-based movement
- Growth mechanic
- Collision detection
- Game loop
- Dynamic speed
- Obstacle system
- State management

---

## 6. 🧩 Functional Requirements

### 6.1 Game Initialization

| ID | FR-01 |
|----|------|
| Description | Khởi tạo game |
| Input | Mở ứng dụng |
| Process | Tạo snake, food, map |
| Output | Hiển thị màn hình game |
| Acceptance Criteria | Game khởi tạo đúng |

---

### 6.2 Player Input

| ID | FR-02 |
|----|------|
| Description | Điều khiển snake |
| Input | WASD / Arrow keys |
| Process | Cập nhật hướng |
| Output | Snake đổi hướng |
| Rules | Không quay đầu |
| Acceptance Criteria | Input delay < 50ms |

---

### 6.3 Movement System

| ID | FR-03 |
|----|------|
| Description | Snake di chuyển |
| Logic | Add head, remove tail |
| Frequency | Theo timer |
| Acceptance Criteria | Di chuyển mượt |

---

### 6.4 Food Interaction

| ID | FR-04 |
|----|------|
| Description | Ăn food |
| Trigger | Head == Food |
| Output | +Score, tăng chiều dài |
| Acceptance Criteria | Food không spawn trùng snake |

---

### 6.5 Collision Detection

| ID | FR-05 |
|----|------|
| Description | Kiểm tra va chạm |
| Cases | Wall, Self, Obstacle |
| Output | Game Over |
| Acceptance Criteria | Chính xác 100% |

---

### 6.6 Score System

| ID | FR-06 |
|----|------|
| Description | Quản lý điểm |
| Features | Current score, high score |
| Acceptance Criteria | Điểm hiển thị chính xác |

---

### 6.7 Pause System

| ID | FR-07 |
|----|------|
| Description | Tạm dừng game |
| Input | Phím P |
| Output | Pause/Resume |
| Acceptance Criteria | Dừng game ngay lập tức |

---

### 6.8 Difficulty Selection

| ID | FR-08 |
|----|------|
| Description | Chọn độ khó |
| Modes | Easy, Medium, Hard |
| Output | Tốc độ thay đổi |
| Acceptance Criteria | Game phản hồi đúng mode |

---

### 6.9 Save Score & Leaderboard

| ID | FR-09 |
|----|------|
| Description | Lưu điểm |
| Storage | File local |
| Output | Hiển thị leaderboard |
| Acceptance Criteria | Dữ liệu lưu đúng |

---

### 6.10 Sound System

| ID | FR-10 |
|----|------|
| Description | Hiệu ứng âm thanh |
| Cases | Eat food, game over |
| Acceptance Criteria | Âm thanh phát đúng |

---

### 6.11 Skin/Theme

| ID | FR-11 |
|----|------|
| Description | Đổi giao diện |
| Options | Snake color, background |
| Acceptance Criteria | Theme đổi realtime |

---

### 6.12 Restart Game

| ID | FR-12 |
|----|------|
| Input | Phím R |
| Output | Reset game |
| Acceptance Criteria | Reset hoàn toàn |

---

## 7. ⚙️ Non-Functional Requirements

### 7.1 Performance
- FPS ≥ 50
- Input latency < 50ms
- Memory usage < 100MB

---

### 7.2 Reliability
- Không crash runtime
- Timer ổn định
- Không mất dữ liệu score

---

### 7.3 Usability
- Giao diện đơn giản
- Không cần tutorial
- Dễ thao tác

---

### 7.4 Maintainability
- Kiến trúc MVC
- Modular code
- Dễ mở rộng feature

---

### 7.5 Portability
- Java 8+
- Cross-platform

---

## 8. 🖥️ UI/UX Requirements

### 8.1 Layout

| Element | Description |
|--------|------------|
| Background | Black/theme |
| Snake | Green/custom |
| Food | Red |
| Score | Top-left |
| Leaderboard | Side panel/dialog |

---

### 8.2 States
- Menu
- Playing
- Pause
- Game Over
- Restart

---

## 9. 🔄 Use Cases

---

### UC-01: Start & Configure Game

#### Actor
Player

#### Description
Người chơi khởi động và cấu hình game trước khi chơi.

#### Includes
- Initialize Snake
- Spawn Food
- Select Difficulty
- Select Skin
- Enable Sound
- Start Game Loop

#### Main Flow
1. User mở game
2. Chọn difficulty
3. Chọn skin/theme
4. Bật/tắt sound
5. Hệ thống khởi tạo game
6. Bắt đầu game loop

---

### UC-02: Control & Play Game

#### Actor
Player

#### Description
Người chơi điều khiển snake trong quá trình chơi.

#### Includes
- Control Snake
- Pause Game
- Resume Game
- Dynamic Speed Increase

#### Main Flow
1. User điều khiển snake
2. Snake di chuyển
3. User có thể pause/resume
4. Tốc độ tăng theo score

---

### UC-03: Collect Food & Update Score

#### Actor
Player

#### Description
Snake ăn food để tăng điểm và chiều dài.

#### Includes
- Eat Food
- Update Score
- Save High Score
- Play Sound Effect

#### Main Flow
1. Snake chạm food
2. Score tăng
3. Snake dài thêm
4. Spawn food mới
5. Phát sound effect
6. Cập nhật leaderboard

---

### UC-04: Handle Collision & Game Over

#### Actor
System

#### Description
Hệ thống xử lý va chạm và kết thúc game.

#### Includes
- Detect Wall Collision
- Detect Self Collision
- Detect Obstacle Collision
- Show Game Over
- Save Final Score

#### Main Flow
1. Hệ thống kiểm tra collision
2. Snake va chạm
3. Hiển thị GAME OVER
4. Lưu điểm cuối cùng

---

### UC-05: Restart & Change Mode

#### Actor
Player

#### Description
Người chơi chơi lại hoặc thay đổi chế độ game.

#### Includes
- Restart Game
- Enable Wall Mode
- Enable Obstacle Mode
- Reset Score
- Reset Snake

#### Main Flow
1. User nhấn Restart
2. Chọn mode mới
3. Hệ thống reset game
4. Bắt đầu lượt chơi mới

---

## 10. 📊 Business Rules

- BR1: Snake không quay đầu 180°
- BR2: Food không spawn vào snake hoặc obstacle
- BR3: Score tăng theo food
- BR4: Dynamic speed tăng theo điểm
- BR5: Leaderboard lưu top score
- BR6: Pause dừng toàn bộ game loop

---

## 11. 🔗 Traceability Matrix

| Requirement | Module |
|------------|--------|
| FR-02 | Input Handler |
| FR-03 | Game Loop |
| FR-05 | Collision Engine |
| FR-06 | Score Manager |
| FR-07 | Pause Controller |
| FR-09 | Leaderboard Manager |
| FR-10 | Sound Manager |

---

## 12. ⚠️ Risks & Mitigation

| Risk | Impact | Solution |
|------|--------|---------|
| Input delay | High | Key Binding |
| Lag khi speed tăng | Medium | Optimize loop |
| File save lỗi | Medium | Backup score |
| Collision bug | High | Unit test |

---

## 13. 🧪 Acceptance Criteria (Overall)

- Game hoạt động ổn định ≥ 10 phút
- Không lỗi input
- Collision chính xác
- Pause hoạt động đúng
- Leaderboard lưu chính xác
- Dynamic speed ổn định
- Restart hoạt động hoàn toàn

---

## 14. 🚀 Future Enhancements

- Multiplayer
- Online leaderboard
- AI snake
- Mobile version
- Cloud save
- Map editor

---

## 15. 📌 Conclusion

Snake Game là một hệ thống game desktop hoàn chỉnh với:
- Input handling
- Game loop
- Collision system
- State management
- Leaderboard
- Dynamic gameplay
- MVC architecture

Hệ thống phù hợp cho việc học phân tích thiết kế hệ thống, UML, MVC và phát triển game desktop bằng Java.
