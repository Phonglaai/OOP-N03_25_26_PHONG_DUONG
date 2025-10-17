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

## 📈 Diagrams

![Sơ đồ chức năng](Sodochucnang.png)
![Sơ đồ thuật toán](Sodothuattoan.png)

## 🚀 Deploy lên Cloud

Xem hướng dẫn chi tiết trong [DEPLOY_GUIDE.md](DEPLOY_GUIDE.md)

**Quick deploy:**
- Railway (Khuyến nghị): 1-click deploy
- Render: Free tier với auto-sleep
- Heroku: Classic PaaS platform
- Docker: Flexible deployment

## 🔒 Bảo mật

⚠️ **Quan trọng:** Không commit database credentials vào Git!

- Dùng environment variables cho sensitive data
- File `.env` đã được thêm vào `.gitignore`
- Tham khảo `.env.example` để setup

## 📝 License

MIT License

## 👥 Contributors

- Nhóm N03 - Phòng Dương
- Học kỳ 25-26

---

**⭐ Nếu project hữu ích, hãy cho một star nhé!**
