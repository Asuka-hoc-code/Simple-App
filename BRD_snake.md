# 🐍 BUSINESS REQUIREMENTS DOCUMENT (BRD)
## Snake Game Desktop Application (Java Swing)

---

## 1. 📌 Executive Summary

Snake Game là một ứng dụng desktop đơn giản được phát triển bằng Java Swing nhằm:
- Cung cấp trải nghiệm giải trí nhẹ
- Minh họa các khái niệm lập trình GUI, game loop, event handling
- Là nền tảng mở rộng cho các tính năng game phức tạp hơn

Ứng dụng hướng tới người dùng cá nhân, sinh viên hoặc người học lập trình.

---

## 2. 🎯 Business Objectives

| ID | Objective | Success Metric |
|----|----------|---------------|
| BO1 | Xây dựng game Snake hoạt động ổn định | Không crash sau 1000 lượt chơi |
| BO2 | Trải nghiệm mượt | FPS ≥ 50 |
| BO3 | Dễ sử dụng | User có thể chơi mà không cần hướng dẫn |
| BO4 | Dễ mở rộng | Code modular, thêm feature < 2h |

---

## 3. 📦 Scope Definition

### 3.1 In Scope
- Game Snake 2D chạy offline
- Điều khiển bằng bàn phím (WASD)
- Tính điểm (score)
- Va chạm (tường, thân)
- Restart game
- Hiển thị trạng thái game

### 3.2 Out of Scope
- Multiplayer
- Online services / API
- Save/load game
- AI opponent
- Đồ họa nâng cao (OpenGL)

---

## 4. 👥 Stakeholders

| Stakeholder | Interest | Responsibility |
|------------|---------|---------------|
| End User | Chơi game | Sử dụng |
| Developer | Code | Implement & maintain |
| Tester | Chất lượng | Test & report bug |
| Instructor | Đánh giá | Review project |

---

## 5. 🎮 Product Overview

### 5.1 Game Concept
Người chơi điều khiển snake di chuyển trong không gian 2D để ăn food và tăng điểm, tránh va chạm.

### 5.2 Core Mechanics
- Movement (grid-based)
- Growth mechanic
- Collision detection
- Game loop (update/render)

---

## 6. 🧩 Functional Requirements

### 6.1 Game Initialization

| ID | FR-01 |
|----|------|
| Description | Khởi tạo game |
| Input | Mở ứng dụng |
| Process | Tạo snake, spawn food |
| Output | Hiển thị màn hình game |
| Acceptance Criteria | Snake hiển thị đúng vị trí |

---

### 6.2 Player Input

| ID | FR-02 |
|----|------|
| Description | Điều khiển snake |
| Input | WASD |
| Process | Cập nhật hướng |
| Output | Snake đổi hướng |
| Rules | Không quay đầu |
| Acceptance Criteria | Phím phản hồi < 50ms |

---

### 6.3 Movement System

| ID | FR-03 |
|----|------|
| Description | Snake di chuyển |
| Logic | Add head, remove tail |
| Frequency | Theo timer |
| Acceptance Criteria | Di chuyển mượt, không giật |

---

### 6.4 Food Interaction

| ID | FR-04 |
|----|------|
| Description | Ăn food |
| Trigger | Head == Food |
| Output | +1 score, tăng chiều dài |
| Acceptance Criteria | Food spawn không trùng snake |

---

### 6.5 Collision Detection

| ID | FR-05 |
|----|------|
| Description | Kiểm tra va chạm |
| Cases | Wall, Self |
| Output | Game Over |
| Acceptance Criteria | Phát hiện chính xác 100% |

---

### 6.6 Score System

| ID | FR-06 |
|----|------|
| Description | Hiển thị điểm |
| Update | Sau mỗi food |
| Acceptance Criteria | Không sai lệch |

---

### 6.7 Game Over

| ID | FR-07 |
|----|------|
| Description | Kết thúc game |
| UI | Hiển thị GAME OVER |
| Acceptance Criteria | Hiển thị trong < 100ms |

---

### 6.8 Restart

| ID | FR-08 |
|----|------|
| Input | Phím R |
| Output | Reset game |
| Acceptance Criteria | Reset hoàn toàn |

---

## 7. ⚙️ Non-Functional Requirements

### 7.1 Performance
- FPS: ≥ 50
- Input latency: < 50ms
- Memory usage: < 100MB

---

### 7.2 Reliability
- Không crash trong runtime
- Timer ổn định

---

### 7.3 Usability
- Không cần tutorial
- Phím đơn giản

---

### 7.4 Maintainability
- Code theo MVC hoặc tách logic rõ ràng
- Comment đầy đủ

---

### 7.5 Portability
- Java 8+
- Cross-platform

---

## 8. 🖥️ UI/UX Requirements

### 8.1 Layout

| Element | Description |
|--------|------------|
| Background | Black |
| Snake | Green |
| Food | Red |
| Score | Top-left |

---

### 8.2 States
- Playing
- Game Over
- Restart

---

## 9. 🔄 Use Cases

---

### UC-01: Start New Game

#### Actor
Player

#### Description
Người chơi khởi động trò chơi mới.

#### Preconditions
- Ứng dụng đã mở

#### Main Flow
1. User mở ứng dụng  
2. Hệ thống khởi tạo snake  
3. Hệ thống spawn food đầu tiên  
4. Hiển thị màn hình game  

#### Postconditions
- Game ở trạng thái Playing

---

### UC-02: Control Snake

#### Actor
Player

#### Description
Người chơi điều khiển hướng di chuyển của snake.

#### Preconditions
- Game đang ở trạng thái Playing

#### Main Flow
1. User nhấn phím WASD  
2. Hệ thống nhận input  
3. Snake đổi hướng di chuyển  

#### Alternative Flow
- Nếu hướng mới ngược 180° → hệ thống bỏ qua input

#### Postconditions
- Snake tiếp tục di chuyển theo hướng mới

---

### UC-03: Eat Food

#### Actor
Player

#### Description
Snake ăn food để tăng điểm và chiều dài.

#### Preconditions
- Snake chạm vào food

#### Main Flow
1. Snake chạm food  
2. Food biến mất  
3. Score tăng +1  
4. Snake tăng chiều dài  
5. Hệ thống spawn food mới  

#### Postconditions
- Điểm số được cập nhật

---

### UC-04: Handle Collision

#### Actor
System

#### Description
Hệ thống kiểm tra va chạm của snake.

#### Preconditions
- Game đang chạy

#### Main Flow
1. Snake va chạm tường hoặc thân  
2. Hệ thống phát hiện collision  
3. Game chuyển sang trạng thái Game Over  
4. Hiển thị thông báo GAME OVER  

#### Postconditions
- Người chơi không thể điều khiển snake

---

### UC-05: Restart Game

#### Actor
Player

#### Description
Người chơi chơi lại sau khi thua.

#### Preconditions
- Game đang ở trạng thái Game Over

#### Main Flow
1. User nhấn phím R  
2. Hệ thống reset score  
3. Reset snake  
4. Spawn food mới  
5. Bắt đầu game mới  

#### Postconditions
- Game quay lại trạng thái Playing

---

## 10. 📊 Business Rules

- BR1: Snake không quay đầu 180°
- BR2: Food spawn trong grid
- BR3: Score tăng 1 mỗi lần ăn
- BR4: Game loop cố định

---

## 11. 🔗 Traceability Matrix

| Requirement | Module |
|------------|--------|
| FR-02 | Input Handler |
| FR-03 | Game Loop |
| FR-05 | Collision Engine |
| FR-06 | UI Renderer |

---

## 12. ⚠️ Risks & Mitigation

| Risk | Impact | Solution |
|------|--------|---------|
| Input không nhận | High | Key Binding |
| Lag | Medium | Optimize timer |
| Bug logic | High | Unit test |

---

## 13. 🧪 Acceptance Criteria (Overall)

- Game chạy ổn định 10 phút liên tục
- Không lỗi input
- Score chính xác
- Restart hoạt động
- UI hiển thị đúng

---

## 14. 🚀 Future Enhancements

- Dynamic speed
- Sound effects
- Pause system
- Leaderboard
- Skin/theme
- AI snake

---

## 15. 📌 Conclusion

Snake Game là một hệ thống nhỏ nhưng đầy đủ các thành phần:
- Input handling
- Game loop
- Rendering
- State management

Phù hợp để học và mở rộng thành game hoàn chỉnh.

---
