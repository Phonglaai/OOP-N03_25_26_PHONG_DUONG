# 🔧 Hướng Dẫn Cài Đặt Maven Trên Windows

## Bước 1: Download Maven ✅ (Đã xong)
Bạn đã download và extract Maven rồi.

## Bước 2: Thêm Maven vào System PATH

### Cách 1: Dùng PowerShell (Nhanh nhất)

**1. Mở PowerShell AS ADMINISTRATOR** (Click phải → Run as Administrator)

**2. Chạy lệnh sau** (thay `C:\apache-maven-3.9.9` bằng đường dẫn thực tế của bạn):

```powershell
# Thêm Maven vào User PATH (khuyến nghị)
$mavenPath = "C:\apache-maven-3.9.9\bin"
[Environment]::SetEnvironmentVariable("Path", $env:Path + ";$mavenPath", "User")

# Hoặc thêm vào System PATH (cần quyền Admin)
[Environment]::SetEnvironmentVariable("Path", $env:Path + ";$mavenPath", "Machine")

# Refresh PATH trong session hiện tại
$env:Path = [System.Environment]::GetEnvironmentVariable("Path","Machine") + ";" + [System.Environment]::GetEnvironmentVariable("Path","User")
```

**3. Kiểm tra:**
```powershell
mvn --version
```

---

### Cách 2: Dùng GUI (Dễ hơn cho người mới)

**Bước 2.1:** Tìm đường dẫn thư mục `bin` của Maven
- Ví dụ: `C:\apache-maven-3.9.9\bin`

**Bước 2.2:** Mở System Environment Variables
- Nhấn `Windows + R`
- Gõ: `sysdm.cpl` → Enter
- Chọn tab **Advanced**
- Click **Environment Variables...**

**Bước 2.3:** Thêm Maven vào PATH
- Trong phần **User variables** (hoặc System variables nếu muốn cho tất cả users)
- Tìm và chọn biến **Path**
- Click **Edit...**
- Click **New**
- Dán đường dẫn: `C:\apache-maven-3.9.9\bin` (thay bằng đường dẫn thực của bạn)
- Click **OK** → **OK** → **OK**

**Bước 2.4:** Đóng và mở lại PowerShell
- **QUAN TRỌNG:** Phải đóng tất cả cửa sổ PowerShell cũ
- Mở PowerShell mới

**Bước 2.5:** Kiểm tra
```powershell
mvn --version
```

Kết quả mong đợi:
```
Apache Maven 3.9.9 (...)
Maven home: C:\apache-maven-3.9.9
Java version: 23.0.2, vendor: Oracle Corporation
...
```

---

## Bước 3: Kiểm tra Java (Quan trọng!)

```powershell
java -version
```

**LƯU Ý:** Project của bạn cần **Java 17**, nhưng bạn đang có Java 23.

### Giải pháp:
1. **Tạm thời:** Sửa `pom.xml` để dùng Java 23
2. **Khuyến nghị:** Download và cài Java 17

---

## Bước 4: Test Maven trong project

```powershell
# Di chuyển vào thư mục project
cd "F:\OOP-N03_25_26_PHONG_DUONG-midtermproject\OOP-N03_25_26_PHONG_DUONG-midtermproject"

# Kiểm tra Maven có hoạt động
mvn --version

# Build project
mvn clean install -DskipTests

# Test kết nối database
mvn spring-boot:run -Dstart-class=demo.TestDatabaseConnection
```

---

## 🐛 Xử lý lỗi thường gặp

### Lỗi: "mvn is not recognized"
- ✅ Kiểm tra đã thêm đúng đường dẫn `\bin` của Maven vào PATH
- ✅ Đã đóng và mở lại PowerShell chưa?
- ✅ Chạy lệnh: `$env:Path` để xem PATH có chứa Maven không

### Lỗi: "JAVA_HOME not set"
```powershell
# Set JAVA_HOME
[Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\Program Files\Java\jdk-23", "User")
$env:JAVA_HOME = "C:\Program Files\Java\jdk-23"
```

### Lỗi: Java version mismatch (Java 23 vs Java 17)
- Tạm thời sửa trong `pom.xml`:
  ```xml
  <java.version>23</java.version>
  <maven.compiler.source>23</maven.compiler.source>
  <maven.compiler.target>23</maven.compiler.target>
  ```

---

## ✅ Checklist sau khi cài

- [ ] `mvn --version` hoạt động
- [ ] `java -version` hiển thị Java version
- [ ] `cd` vào thư mục project thành công
- [ ] `mvn clean install -DskipTests` chạy không lỗi
- [ ] `mvn spring-boot:run` khởi động được ứng dụng

---

## 🎯 Lệnh đầy đủ để test ngay:

```powershell
# 1. Kiểm tra Maven
mvn --version

# 2. Di chuyển vào project
cd "F:\OOP-N03_25_26_PHONG_DUONG-midtermproject\OOP-N03_25_26_PHONG_DUONG-midtermproject"

# 3. Build project
mvn clean install -DskipTests

# 4. Test database connection
mvn spring-boot:run -Dstart-class=demo.TestDatabaseConnection
```

---

## 💡 Tip: Nếu không muốn config PATH

Bạn có thể chạy Maven trực tiếp bằng đường dẫn đầy đủ:

```powershell
C:\apache-maven-3.9.9\bin\mvn.cmd --version
C:\apache-maven-3.9.9\bin\mvn.cmd clean install
```

Nhưng khuyến nghị nên thêm vào PATH để tiện hơn!
