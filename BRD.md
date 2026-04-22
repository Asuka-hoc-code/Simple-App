BUSINESS REQUIREMENT DOCUMENT (BRD)
GAME: RẮN SĂN MỒI
1. Thông tin chung
Tên dự án: Snake Game (Rắn Săn Mồi)
Phiên bản: 1.0
Ngày tạo: 22/04/2026
Người phụ trách: Product Owner
Trạng thái: Draft
2. Mục tiêu (Business Objectives)
Phát triển một game giải trí đơn giản, dễ chơi, phù hợp với mọi độ tuổi.
Tăng trải nghiệm người dùng thông qua gameplay mượt mà và gây nghiện.
Tạo nền tảng có thể mở rộng thêm tính năng trong tương lai (leaderboard, multiplayer,...).
3. Phạm vi sản phẩm (Scope)
Trong phạm vi (In-scope):
Game 2D, góc nhìn từ trên xuống.
Điều khiển rắn di chuyển để ăn mồi.
Tăng chiều dài khi ăn mồi.
Tính điểm và hiển thị điểm số.
Kết thúc game khi va chạm.
Ngoài phạm vi (Out-of-scope):
Multiplayer online.
Giao dịch trong game (in-app purchase).
Tài khoản người dùng.
4. Đối tượng người dùng (Target Users)
Người chơi casual (giải trí nhẹ).
Học sinh, sinh viên.
Người dùng không yêu cầu kỹ năng cao.
5. Tính năng chức năng (Functional Requirements)
ID	Tính năng	Mô tả	Ưu tiên
F01	Bắt đầu game	Cho phép người chơi bắt đầu ván mới	High
F02	Điều khiển rắn	Di chuyển lên/xuống/trái/phải	High
F03	Sinh mồi	Mồi xuất hiện ngẫu nhiên	High
F04	Ăn mồi	Rắn dài ra và tăng điểm	High
F05	Va chạm	Game over khi đụng tường hoặc thân	High
F06	Tính điểm	Hiển thị điểm hiện tại	High
F07	High Score	Lưu điểm cao nhất	Medium
F08	Pause/Resume	Tạm dừng và tiếp tục game	Medium
F09	Âm thanh	Bật/tắt âm thanh	Low
F10	Độ khó	Tùy chỉnh tốc độ game	Low
6. Luật chơi (Game Rules)
Rắn di chuyển liên tục theo hướng hiện tại.
Người chơi thay đổi hướng bằng phím điều khiển.
Mỗi lần ăn mồi:
Rắn dài thêm 1 đơn vị
Điểm tăng
Game kết thúc khi:
Rắn đâm vào tường
Rắn cắn vào chính mình
Người chơi có thể chơi lại sau khi thua.
7. Dữ liệu & chỉ số (Data Requirements)
Dữ liệu	Mô tả	Lưu trữ
Điểm hiện tại	Điểm trong ván chơi	Runtime
Điểm cao nhất	Điểm cao nhất đạt được	Local Storage
Cài đặt	Âm thanh, độ khó	Local Storage
8. Yêu cầu phi chức năng (Non-functional Requirements)
Hiệu năng
Game chạy mượt (≥ 60 FPS).
Không lag trên thiết bị trung bình.
Tương thích
Hỗ trợ:
Android
iOS
Web (optional)
Khả dụng (Usability)
Giao diện đơn giản, dễ hiểu.
Thời gian học chơi < 1 phút.
Bảo trì (Maintainability)
Code dễ đọc, dễ mở rộng.
9. Giả định & phụ thuộc (Assumptions & Dependencies)
Không cần đăng nhập.
Dữ liệu lưu cục bộ.
Không cần internet để chơi.
10. Tính năng tương lai (Future Enhancements)
Leaderboard online
Nhiều chế độ chơi (Time mode, Challenge mode)
Skin cho rắn
Multiplayer
11. Tiêu chí chấp nhận (Acceptance Criteria)
Người chơi có thể chơi từ đầu đến cuối mà không lỗi.
Điểm được tính chính xác.
Game over xảy ra đúng điều kiện.
High score được lưu sau khi thoát game.
