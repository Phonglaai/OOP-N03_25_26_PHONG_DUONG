# App Order Nước Mía 🥤

Ứng dụng quản lý đơn hàng nước mía được xây dựng bằng Spring Boot 3.5.0 và MySQL.

## 🚀 Tech Stack

- **Java 21** (Eclipse Adoptium)
- **Spring Boot 3.5.0** (Spring Framework 6.2.x)
- **MySQL 8.0** (Aiven Cloud)
- **Maven 3.9.11**
- **Thymeleaf** (Template Engine)
- **Spring Data JPA** (ORM)

## 📋 Yêu cầu hệ thống

- Java 21 hoặc mới hơn
- Maven 3.9.x
- MySQL 8.0+ (hoặc dùng Aiven MySQL)

## ⚙️ Cài đặt và Chạy Local

### 1. Clone repository

```bash
git clone https://github.com/Phonglaai/OOP-N03_25_26_PHONG_DUONG.git
cd OOP-N03_25_26_PHONG_DUONG-midtermproject
```

### 2. Cấu hình Database

Tạo file `.env` hoặc set environment variables:

```bash
# Windows PowerShell
$env:DATABASE_URL="jdbc:mysql://your-host:port/database?useSSL=true"
$env:DATABASE_USERNAME="your-username"
$env:DATABASE_PASSWORD="your-password"
```

Hoặc copy `.env.example` thành `.env` và điền thông tin:

```bash
cp .env.example .env
# Edit .env với thông tin database của bạn
```

### 3. Build và Run

```bash
# Build project
mvn clean package

# Run application
mvn spring-boot:run
```

Ứng dụng sẽ chạy tại: http://localhost:8080

## 🏗️ Cấu trúc Project

```
src/main/java/demo/
├── DemoApplication.java          # Main application
├── config/
│   └── DatabaseConnection.java   # Database config
├── controller/
│   ├── DoanhThuController.java   # Revenue controller
│   ├── KhachHangController.java  # Customer controller
│   └── SanPhamController.java    # Product controller
├── model/
│   ├── DoanhThu.java             # Revenue entity
│   ├── DonHang.java              # Order entity
│   ├── KhachHang.java            # Customer entity
│   └── SanPham.java              # Product entity
├── repository/
│   ├── DoanhThuRepository.java   # Revenue repository
│   ├── DonHangRepository.java    # Order repository
│   ├── KhachHangRepository.java  # Customer repository
│   └── SanPhamRepository.java    # Product repository
└── service/
    ├── DoanhThuService.java      # Revenue service
    ├── DonHangService.java       # Order service
    ├── KhachHangService.java     # Customer service
    └── SanPhamService.java       # Product service
```

## 📊 Các chức năng chính

### KHÁCH HÀNG

- **STT**: Số thứ tự của khách hàng
- **Tên**: Tên khách hàng
- **Số điện thoại**: Số điện thoại khách

**Chức năng:**
- `taokhachhang()`: Tạo thông tin khách hàng
- `hienthitt()`: Hiển thị lịch sử mua hàng
- `capnhattt()`: Chỉnh sửa thông tin cá nhân
- `xoatt()`: Xóa thông tin khách hàng

### SẢN PHẨM

- **ID**: Mã sản phẩm
- **Tên sản phẩm**: Tên sản phẩm khách đặt
- **Số lượng**: Số lượng sản phẩm đã đặt
- **Đơn giá**: Giá thành

**Chức năng:**
- `themsp()`: Thêm sản phẩm mới vào menu
- `hienthisp()`: Hiển thị thông tin sản phẩm
- `capnhatsp()`: Chỉnh sửa thông tin, giá thành sản phẩm
- `xoasp()`: Loại bỏ sản phẩm khỏi danh sách

### DOANH THU

- **Thời gian**: Thời gian ghi nhận doanh thu cuối ngày
- **Tổng tiền**: Tổng doanh thu đã đạt được
- **Số đơn**: Tổng số đơn hàng đã bán

**Chức năng:**
- `taodoanhthu()`: Thêm doanh thu
- `xemchitiet()`: Xem cụ thể thông tin đơn hàng
- `capnhatdoanhthu()`: Cập nhật doanh thu
- `xoadoanhthu()`: Loại bỏ báo cáo theo thời gian doanh thu

## � UML Diagrams

### Structural Diagram (Class Diagram)
- [Class Diagram](diagrams/class-diagram.puml) - Sơ đồ cấu trúc 4 đối tượng chính và mối quan hệ

### Sequence Diagrams (CRUD Operations)
- [CRUD Khách Hàng](diagrams/sequence-crud-khachhang.puml) - Thao tác CRUD cho Khách Hàng
- [CRUD Sản Phẩm](diagrams/sequence-crud-sanpham.puml) - Thao tác CRUD cho Sản Phẩm  
- [CRUD Đơn Hàng](diagrams/sequence-crud-donhang.puml) - Thao tác CRUD cho Đơn Hàng
- [CRUD Doanh Thu](diagrams/sequence-crud-doanhthu.puml) - Thao tác CRUD cho Doanh Thu

### Main Function (Core Business Logic)
- [Core Function Diagram](diagrams/sequence-main-function.puml) - **Sơ đồ thuật toán chức năng chính (lõi)** - Liên kết 4 đối tượng: KhachHang → DonHang → SanPham → DoanhThu

📝 **Xem hướng dẫn generate PNG:** [diagrams/README.md](diagrams/README.md)

**Tổng cộng:** 1 Structural + 5 Sequence diagrams ✅

## 🚀 Deploy lên Cloud

Xem hướng dẫn chi tiết trong [DEPLOY_GUIDE.md](DEPLOY_GUIDE.md)

**Quick deploy:**
- Railway (Khuyến nghị): 1-click deploy
- Render: Free tier với auto-sleep
- Heroku: Classic PaaS platform
- Docker: Flexible deployment

## � Links

- **GitHub Repository:** [https://github.com/Phonglaai/OOP-N03_25_26_PHONG_DUONG](https://github.com/Phonglaai/OOP-N03_25_26_PHONG_DUONG)
- **Video Demo:** _(Chưa có - Thêm link YouTube sau khi quay video)_
- **Deployed URL:** _(Chưa có - Thêm link sau khi deploy)_

## ✅ Midterm Requirements Checklist

- [x] **Yêu cầu 1:** Sử dụng MVC framework (Spring Boot) ✓
- [x] **Yêu cầu 2:** UML Diagrams (1 Structural + 5 Sequences) ✓
- [x] **Yêu cầu 3:** Ít nhất 4 đối tượng (KhachHang, SanPham, DonHang, DoanhThu) ✓
- [x] **Yêu cầu 4:** CRUD cho 3 đối tượng (4/4 đối tượng có CRUD) ✓
- [x] **Yêu cầu 5:** Phương thức hoạt động chính (Core function: `capNhatDoanhThuTuDonHang`) ✓
- [x] **Yêu cầu 6:** Exception handling (MyException + MyGlobal) ✓
- [x] **Yêu cầu 7:** Database interaction (JPA + MySQL) ✓
- [x] **Yêu cầu 8:** GUI với Spring Boot (Thymeleaf templates) ✓
- [x] **Yêu cầu 9:** README file ✓

## �🔒 Bảo mật

⚠️ **Quan trọng:** Không commit database credentials vào Git!

- Dùng environment variables cho sensitive data
- File `.env` đã được thêm vào `.gitignore`
- Tham khảo `.env.example` để setup

## 📝 License

MIT License

## 👥 Contributors

- **Nhóm N03 - Phòng Dương**
- **Học kỳ:** 2025-2026
- **Môn:** Lập trình hướng đối tượng (OOP)
- **Members:**
  - _Thêm tên thành viên và vai trò tại đây_

---

**⭐ Nếu project hữu ích, hãy cho một star nhé!**
