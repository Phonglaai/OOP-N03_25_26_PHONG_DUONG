# UML Diagrams - App Order Nước Mía

Thư mục này chứa tất cả UML diagrams cho dự án, được viết bằng PlantUML format.

## 📋 Danh sách Diagrams

### 1. Structural Diagram (Class Diagram)
- **File:** `class-diagram.puml`
- **Mô tả:** Sơ đồ lớp mô tả cấu trúc 4 đối tượng chính và mối quan hệ giữa chúng
- **Nội dung:**
  - 4 Entity classes: KhachHang, SanPham, DonHang, DoanhThu
  - 4 Repository interfaces
  - 4 Service classes
  - Exception handling (MyException, MyGlobal)
  - Relationships: OneToMany, ManyToOne

### 2. Sequence Diagram - CRUD KhachHang
- **File:** `sequence-crud-khachhang.puml`
- **Mô tả:** Sơ đồ tuần tự cho các thao tác CRUD của Khách Hàng
- **Operations:** CREATE, READ (all, by ID), UPDATE, DELETE

### 3. Sequence Diagram - CRUD SanPham
- **File:** `sequence-crud-sanpham.puml`
- **Mô tả:** Sơ đồ tuần tự cho các thao tác CRUD của Sản Phẩm
- **Operations:** CREATE, READ (all, active), UPDATE (info, stock), DELETE (soft delete)

### 4. Sequence Diagram - CRUD DonHang
- **File:** `sequence-crud-donhang.puml`
- **Mô tả:** Sơ đồ tuần tự cho các thao tác CRUD của Đơn Hàng
- **Operations:** CREATE (with stock update), READ (all, by customer, by status), UPDATE (complete), DELETE (cancel + refund stock)

### 5. Sequence Diagram - CRUD DoanhThu
- **File:** `sequence-crud-doanhthu.puml`
- **Mô tả:** Sơ đồ tuần tự cho các thao tác CRUD của Doanh Thu
- **Operations:** CREATE, READ (today, by date range, top), UPDATE (manual, auto-sync), DELETE

### 6. Sequence Diagram - Main Function (Core)
- **File:** `sequence-main-function.puml`
- **Mô tả:** **Sơ đồ thuật toán chức năng chính (lõi) của ứng dụng**
- **Flow:** 
  1. Khách hàng đặt hàng
  2. Giảm tồn kho sản phẩm
  3. Hoàn thành đơn hàng
  4. **Tự động cập nhật doanh thu** (Core function)
  5. Xem báo cáo doanh thu
- **Liên kết 4 đối tượng:** KhachHang → DonHang → SanPham → DoanhThu

## 🔧 Cách Generate PNG từ PlantUML

### Option 1: Sử dụng VS Code Extension (Khuyến nghị)
1. Cài extension: **PlantUML** (jebbs.plantuml)
2. Mở file `.puml` bất kỳ
3. Press `Alt + D` hoặc Right-click → "Preview Current Diagram"
4. Export: Click icon "Export" → chọn PNG

### Option 2: Sử dụng Online Editor
1. Truy cập: https://www.plantuml.com/plantuml/uml/
2. Copy nội dung file `.puml`
3. Paste vào editor
4. Click "Submit" để generate
5. Download PNG

### Option 3: Sử dụng Command Line
```bash
# Cài PlantUML
# Download plantuml.jar từ https://plantuml.com/download

# Generate single file
java -jar plantuml.jar class-diagram.puml

# Generate all diagrams
java -jar plantuml.jar *.puml

# Generate with output directory
java -jar plantuml.jar -o ../images *.puml
```

### Option 4: Sử dụng npm package
```bash
# Install
npm install -g node-plantuml

# Generate
puml generate class-diagram.puml -o class-diagram.png

# Generate all
puml generate *.puml
```

## 📁 Cấu trúc thư mục đề xuất

```
diagrams/
├── *.puml                          # PlantUML source files
├── images/                         # Generated PNG images
│   ├── class-diagram.png
│   ├── sequence-crud-khachhang.png
│   ├── sequence-crud-sanpham.png
│   ├── sequence-crud-donhang.png
│   ├── sequence-crud-doanhthu.png
│   └── sequence-main-function.png
└── README.md                       # This file
```

## 🎨 PlantUML Syntax Reference

### Useful links:
- Documentation: https://plantuml.com/
- Class Diagram: https://plantuml.com/class-diagram
- Sequence Diagram: https://plantuml.com/sequence-diagram
- Online Demo: https://www.plantuml.com/plantuml/uml/

### Common syntax:
```plantuml
' Class diagram
class ClassName {
    - privateField: Type
    + publicMethod(): ReturnType
}

' Sequence diagram
actor User
participant Service
User -> Service: method()
activate Service
Service --> User: response
deactivate Service

' Relationships
ClassA "1" -- "many" ClassB : relationship
ClassA ..> ClassB : depends
ClassA --> ClassB : uses
```

## ✅ Validation Checklist

- [x] 1 Structural diagram (Class Diagram) ✓
- [x] 4 CRUD Sequence diagrams (KhachHang, SanPham, DonHang, DoanhThu) ✓
- [x] 1 Main function Sequence diagram (Core business logic) ✓
- [x] Total: 6 diagrams = 1 + 5 ✓

**Đáp ứng yêu cầu:** Ít nhất 01 Sơ đồ chức năng (Structural) và ít nhất 05 sơ đồ thuật toán (Sequences)

---

**Note:** Tất cả diagrams đã được tối ưu với:
- Vietnamese labels và comments
- Clear flow và logic
- Exception handling
- Database interactions
- Business rules validation
