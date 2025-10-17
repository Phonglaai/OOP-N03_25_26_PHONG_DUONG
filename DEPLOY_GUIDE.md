# 🚀 Hướng Dẫn Deploy Spring Boot App Lên Cloud

Project này đã được chuẩn bị sẵn sàng để deploy lên các nền tảng cloud phổ biến.

---

## ⚡ Option 1: Railway (Khuyến nghị - Dễ nhất)

### Bước 1: Tạo tài khoản
1. Truy cập https://railway.app
2. Sign up với GitHub account
3. Verify email

### Bước 2: Deploy
```bash
# 1. Install Railway CLI (Optional)
npm install -g @railway/cli

# 2. Login
railway login

# 3. Initialize project
railway init

# 4. Deploy
railway up
```

### Hoặc deploy qua Web UI:
1. Vào https://railway.app/new
2. Chọn **"Deploy from GitHub repo"**
3. Connect GitHub và chọn repository này
4. Railway sẽ tự động detect Spring Boot và deploy!

### Environment Variables trên Railway:
```
DATABASE_URL=jdbc:mysql://your-aiven-mysql-host:port/defaultdb?useSSL=true&requireSSL=true
DATABASE_USERNAME=your-username
DATABASE_PASSWORD=your-password
```

> ⚠️ **Lưu ý:** Thay `your-aiven-mysql-host`, `your-username`, `your-password` bằng thông tin thật từ Aiven MySQL của bạn.

### Xem logs:
```bash
railway logs
```

---

## 🎨 Option 2: Render

### Bước 1: Tạo tài khoản
1. Truy cập https://render.com
2. Sign up với GitHub

### Bước 2: Deploy
1. Click **"New +"** → **"Web Service"**
2. Connect GitHub repository
3. Cấu hình:
   - **Name**: `app-nuoc-mia`
   - **Environment**: `Java`
   - **Build Command**: `mvn clean package -DskipTests`
   - **Start Command**: `java -jar target/app-nuoc-mia-1.0.0.jar`
   - **Instance Type**: Free

4. Thêm Environment Variables (giống Railway ở trên)
5. Click **"Create Web Service"**

### Free tier:
- 750 giờ/tháng miễn phí
- App sleep sau 15 phút không hoạt động
- Wake up khi có request (~30s)

---

## 🟣 Option 3: Heroku

### Cài đặt Heroku CLI:
```powershell
# Windows (dùng scoop)
scoop install heroku-cli

# Hoặc download từ: https://devcenter.heroku.com/articles/heroku-cli
```

### Deploy:
```bash
# 1. Login
heroku login

# 2. Create app
heroku create app-nuoc-mia-demo

# 3. Add buildpack
heroku buildpacks:set heroku/java

# 4. Set config vars (thay YOUR_xxx bằng thông tin thật)
heroku config:set DATABASE_URL="jdbc:mysql://YOUR_MYSQL_HOST:PORT/defaultdb?useSSL=true&requireSSL=true"
heroku config:set DATABASE_USERNAME=YOUR_USERNAME
heroku config:set DATABASE_PASSWORD=YOUR_PASSWORD

# 5. Deploy
git push heroku master

# 6. Open app
heroku open

# 7. View logs
heroku logs --tail
```

---

## 🐳 Option 4: Docker + Cloud (Flexible nhất)

### Tạo Dockerfile:
```dockerfile
FROM eclipse-temurin:21-jdk-alpine as builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN apk add --no-cache maven
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/app-nuoc-mia-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Build và run local:
```bash
docker build -t app-nuoc-mia .
docker run -p 8080:8080 -e PORT=8080 app-nuoc-mia
```

### Deploy to:

**Google Cloud Run:**
```bash
gcloud run deploy app-nuoc-mia --source .
```

**AWS App Runner:**
```bash
aws apprunner create-service --service-name app-nuoc-mia
```

**Azure Container Apps:**
```bash
az containerapp up --name app-nuoc-mia --source .
```

---

## 📱 Option 5: GitHub Codespaces (Development)

Không phải deploy production nhưng có thể code và test trên cloud:

1. Vào repository trên GitHub
2. Click **Code** → **Codespaces** → **Create codespace**
3. VS Code online tự động mở
4. Run: `mvn spring-boot:run`
5. Port 8080 sẽ tự động forward

---

## 🔐 Bảo mật Database Credentials

### Option A: Environment Variables (Khuyến nghị)

Thay vì hardcode trong `application.properties`, dùng env vars:

```properties
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
```

### Option B: Spring Cloud Config

Dùng central config server cho nhiều environments.

### Option C: Secrets Manager

- AWS Secrets Manager
- Azure Key Vault
- Google Secret Manager

---

## 📊 So sánh các Platform

| Platform | Free Tier | Dễ dùng | Tốc độ | Khuyến nghị |
|----------|-----------|---------|--------|-------------|
| **Railway** | $5 credit/tháng | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ✅ Tốt nhất |
| **Render** | 750h/tháng | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ✅ Backup tốt |
| **Heroku** | Sleep sau 30m | ⭐⭐⭐ | ⭐⭐⭐ | ⚠️ Limited |
| **Fly.io** | 3 apps miễn phí | ⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ✅ Tốt cho Asia |
| **Google Cloud Run** | 2M requests/tháng | ⭐⭐⭐ | ⭐⭐⭐⭐⭐ | 💰 Pay as you go |

---

## 🎯 Khuyến nghị

**Cho người mới:** Railway hoặc Render
**Cho production:** Google Cloud Run hoặc AWS
**Cho learning:** Railway + GitHub Codespaces

---

## 🐛 Troubleshooting

### App không start?
```bash
# Check logs
railway logs
# hoặc
heroku logs --tail
```

### Database connection failed?
- Kiểm tra environment variables
- Kiểm tra firewall rules của Aiven
- Verify SSL certificates

### Port binding error?
- Đảm bảo `server.port=${PORT:8080}` trong application.properties
- Check platform có set PORT env var không

---

## 📞 Hỗ trợ

Nếu gặp vấn đề:
1. Check logs của platform
2. Verify database credentials
3. Test local trước: `mvn spring-boot:run`
4. Check platform status page

---

## 🎉 Sau khi deploy thành công

Bạn sẽ có URL dạng:
- Railway: `https://app-nuoc-mia-production.up.railway.app`
- Render: `https://app-nuoc-mia.onrender.com`
- Heroku: `https://app-nuoc-mia-demo.herokuapp.com`

Test bằng cách truy cập: `https://your-url.com/`

**Chúc mừng bạn đã deploy thành công! 🚀**
