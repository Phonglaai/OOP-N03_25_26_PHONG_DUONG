package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("✅ Ứng dụng Spring Boot đã khởi động thành công!");
        System.out.println("🌐 Truy cập: http://localhost:8080/");
    }
}
