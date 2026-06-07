# BUSINESS REQUIREMENT DOCUMENT (BRD)
## GAME: RẮN SĂN MỒI

### 1. Thông tin chung
- **Tên dự án:** Snake Game
- **Phiên bản:** 1.0
- **Ngày tạo:** 22/04/2026
- **Trạng thái:** Draft

---

### 2. Mục tiêu
- Phát triển game giải trí đơn giản  
- Dễ chơi, phù hợp mọi độ tuổi  
- Có khả năng mở rộng trong tương lai  

---

### 3. Phạm vi sản phẩm

#### In-scope:
- Game 2D  
- Điều khiển rắn ăn mồi  
- Tính điểm  
- Game Over khi va chạm  

#### Out-of-scope:
- Multiplayer  
- Đăng nhập  
- Giao dịch trong game  

---

### 4. Functional Requirements

| ID  | Tính năng | Mô tả | Ưu tiên |
|-----|----------|------|--------|
| F01 | Start Game | Bắt đầu game | High |
| F02 | Control | Điều khiển rắn | High |
| F03 | Food | Sinh mồi | High |
| F04 | Eat Food | Rắn dài ra và tăng điểm | High |
| F05 | Collision | Va chạm → Game Over | High |
| F06 | Score | Hiển thị điểm | High |
| F07 | High Score | Lưu điểm cao nhất | Medium |
| F08 | Pause | Tạm dừng game | Medium |
| F09 | Sound | Bật/tắt âm thanh | Low |
| F10 | Difficulty | Chọn độ khó | Low |

---

### 5. Luật chơi
- Rắn di chuyển liên tục  
- Người chơi điều khiển hướng đi  
- Mỗi lần ăn mồi:
  - Rắn dài thêm  
  - Tăng điểm  
- Game kết thúc khi:
  - Đâm vào tường  
  - Đâm vào chính mình  

---

### 6. Dữ liệu

| Dữ liệu | Mô tả |
|--------|------|
| Score | Điểm hiện tại |
| High Score | Điểm cao nhất |
| Settings | Âm thanh, độ khó |

---

### 7. Non-functional Requirements

#### Hiệu năng
- Game chạy mượt (>= 60 FPS)

#### Khả dụng
- Giao diện đơn giản  
- Dễ học  

#### Tương thích
- Android  
- iOS  
- Web (optional)  

---

### 8. Future Features
- Leaderboard online  
- Skin cho rắn  
- Nhiều chế độ chơi  

---

### 9. Acceptance Criteria
- Game chạy không lỗi  
- Tính điểm chính xác  
- Game Over đúng điều kiện  
- Lưu High Score thành công  
