# Ứng dụng chat
* MSSV: 20424073
* Họ và Tên: Võ Sĩ Thiên
* Email: vosithien1234@gmail.com
## Các bước cài đặt
1. Sử dụng IDE (NetBean 8.2 hoặc công cụ khác) để mở 2 project 
2. Open 2 project (Chat_client, Chat_server)
3. Sau đó add libary sql server ( mssql-jdbc-8.2.2.jre8 ) đây là file jar được download về, nếu sài jdk 8.2, nếu sài JDK ca hơn thì chọn file tương ứng.
4. Sau đó chạy file Chat_server/Source Packages/Chat_server/Main_server.java
5. Sau đó hiển thị form đăng nhâp - > Nhập port vào nhấn nút login
6. Sau đó chạy file Chat_client/Source Packages/chat_Client/Main_client.java
7. Lúc này sẽ hiển thì form đăng nhập vào server, gồm có ( PORT và IP Address), sau đó nhập PORT vừa nhập bên server và địa chỉ IP của máy đang chạy sau đó nhấn login
8. Sau đó sẽ hiển thị form đăng nhập gôm (username và password và có 2 nút login, register) nếu chưa có tài khoản thì nhấn đăng ký
9. Tài khoản mật khẩu có trong database
10. Sau khi đăng nhập sẽ hiển thị trang home của client
11. Vậy là có thể sử dụng.
## Các chức năng làm được:
1. Đăng ký tài khoản
2. Đăng nhập với tài khoản đăng ký
3. Chat
4. Chat cùng lúc với nhiều người(mở nhiều cửa sổ)
