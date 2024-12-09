# Triple Wolves - Phần mềm Quản lý Thư viện
Phần mềm Quản lý Thư viện là một hệ thống giúp tổ chức và quản lý sách trong thư viện, cung cấp các chức năng quản lý sách, người dùng và mượn trả sách. Phần mềm bao gồm hai vai trò chính: **Admin** và **User**, mỗi vai trò có những chức năng và quyền lợi riêng biệt.
Đây cũng là sản phẩm cho bài tập lớn môn Lập trình hướng đối tượng (INT2204 7).
## Tác giả
1. Phạm Hùng Dũng
2. Phan Bá Thọ
3. Trần Đình Phước Sơn
## Chức Năng Chính

### 1. **Admin**
Admin có quyền truy cập và quản lý các tính năng sau:

- **Quản lý số lượng sách**: Admin có thể xem tổng số sách hiện có trong thư viện, bao gồm cả các sách đã được mượn.
- **Quản lý sách đã mượn và đang mượn**: Admin có thể theo dõi tình trạng mượn sách của các user, bao gồm những sách đã mượn và các sách đang mượn.
- **Quản lý thông tin người dùng**: Admin có quyền xem thông tin của tất cả người dùng trong hệ thống.
- **Quản lý và sửa thông tin sách**: Admin có thể thêm, sửa hoặc xóa thông tin của các sách trong hệ thống.
- **Duyệt request mượn sách**: Admin sẽ nhận được yêu cầu mượn sách từ người dùng và duyệt hoặc từ chối yêu cầu đó.
- **Chat với user**: Admin có thể giao tiếp với người dùng qua hệ thống chat, giải đáp thắc mắc hoặc hỗ trợ người dùng.

### 2. **User**
User có quyền sử dụng các tính năng sau:

- **Mượn sách**: User có thể duyệt danh mục sách và gửi yêu cầu mượn sách. Yêu cầu sẽ được Admin duyệt hoặc từ chối.
- **Trả sách**: Khi đã sử dụng xong, user có thể trả lại sách đã mượn cho thư viện.
- **Chat với các user khác**: User có thể giao tiếp với những người dùng khác trong hệ thống qua tính năng chat.

### Yêu Cầu Hệ Thống

- **Hệ điều hành**: Windows, macOS, Linux
- **Ngôn ngữ lập trình**: Java 8 trở lên
- **Công cụ phát triển**: 
  - **IDE**: IntelliJ IDEA, Eclipse, hoặc NetBeans
  - **Thư viện**: JavaFX
- **Công cụ cần thiết**:
  - **Google Books API**

### Cài Đặt

1. **Clone repository**:
   ```bash
   git clone https://github.com/phuocsonuet/LibraryManagementApp2](https://github.com/phan-tho/LibraryManagementApp

2. **Chạy ứng dụng**:
   Mở dự án trong IDE và chạy lớp **Main.java** hoặc sử dụng lệnh **mvn clean install** (nếu sử dụng Maven).
   Ứng dụng sẽ tự động mở giao diện JavaFX.

### Công Nghệ Sử Dụng
- **Ngôn ngữ lập trình**: Java
- **Giao diện người dùng**: JavaFX
- **API: Google Books API** 
- **Cơ sở dữ liệu:** SQLite
