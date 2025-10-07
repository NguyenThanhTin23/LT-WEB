# Customer Support Chat (Spring Boot + WebSocket + STOMP)

## Cơ chế hoạt động WebSocket/STOMP
- WebSocket tạo kết nối hai chiều lâu dài giữa client và server trên một TCP socket.
- STOMP là giao thức nhắn tin text-based chạy trên WebSocket, chuẩn hóa đường dẫn đích gửi/nhận.
- Client kết nối endpoint `/ws` (SockJS fallback). App gửi tới `/app/**`. Broker nội bộ phát tới `/topic/**` hoặc `/queue/**`.
- Mô hình ở đây dùng phòng theo `roomId`. Cả khách hàng và tư vấn viên subscribe `/topic/room.{roomId}`. Gửi tin tới `/app/room/{roomId}/send`.

## Cách chạy
```bash
mvn -v
mvn spring-boot:run
# Hoặc: mvn clean package && java -jar target/customer-support-chat-1.0.0.jar
```
Mặc định chạy trên http://localhost:8080/

## Demo nhanh
1. Mở 2 tab trình duyệt tới `/`.
2. Tab 1 chọn role `customer`, nhập `roomId` như `order-123`, tên bất kỳ → Kết nối.
3. Tab 2 chọn role `agent`, nhập cùng `roomId` → Kết nối.
4. Nhắn tin hai phía, tin hiển thị ở khung chat.

## Cấu trúc
- `WebSocketConfig` bật STOMP, endpoint `/ws`, simple broker `/topic`, `/queue`.
- `ChatController` xử lý `/app/room/{roomId}/send` và `/join`, phát về `/topic/room.{roomId}`.
- Giao diện tại `templates/index.html`, JS ở `static/js/chat.js`, dùng webjars cho SockJS và STOMP.

## Gợi ý mở rộng
- Lưu hội thoại vào DB theo `roomId`.
- Thêm xác thực JWT và gán `role` từ token.
- Tạo trang dashboard dành cho agent liệt kê các phòng đang hoạt động.
