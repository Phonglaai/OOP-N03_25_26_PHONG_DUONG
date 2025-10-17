package demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Test class để kiểm tra kết nối database
 * Chạy class này để verify connection tới Aiven MySQL
 */
@SpringBootApplication
public class TestDatabaseConnection implements CommandLineRunner {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public TestDatabaseConnection(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestDatabaseConnection.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n========================================");
        System.out.println("🔍 KIỂM TRA KẾT NỐI DATABASE");
        System.out.println("========================================\n");

        try {
            // Test 1: Kiểm tra DataSource
            System.out.println("✓ DataSource: " + dataSource.getClass().getName());
            
            // Test 2: Lấy connection và kiểm tra
            var connection = dataSource.getConnection();
            System.out.println("✓ Connection thành công!");
            System.out.println("  - Database: " + connection.getCatalog());
            System.out.println("  - URL: " + connection.getMetaData().getURL());
            System.out.println("  - Driver: " + connection.getMetaData().getDriverName());
            System.out.println("  - Version: " + connection.getMetaData().getDriverVersion());
            
            // Test 3: Thực hiện query đơn giản
            String result = jdbcTemplate.queryForObject("SELECT 'Kết nối thành công!' as message", String.class);
            System.out.println("✓ Query test: " + result);
            
            // Test 4: Kiểm tra MySQL version
            String mysqlVersion = jdbcTemplate.queryForObject("SELECT VERSION()", String.class);
            System.out.println("✓ MySQL Version: " + mysqlVersion);
            
            // Test 5: Liệt kê các bảng hiện có
            System.out.println("\n📊 Các bảng trong database:");
            var tables = jdbcTemplate.queryForList(
                "SELECT table_name FROM information_schema.tables WHERE table_schema = DATABASE()",
                String.class
            );
            
            if (tables.isEmpty()) {
                System.out.println("  (Chưa có bảng nào - sẽ được tạo khi chạy ứng dụng)");
            } else {
                tables.forEach(table -> System.out.println("  - " + table));
            }
            
            connection.close();
            
            System.out.println("\n========================================");
            System.out.println("✅ TẤT CẢ TESTS ĐÃ PASS!");
            System.out.println("========================================\n");
            
        } catch (Exception e) {
            System.err.println("\n========================================");
            System.err.println("❌ LỖI KẾT NỐI DATABASE");
            System.err.println("========================================");
            System.err.println("Chi tiết lỗi: " + e.getMessage());
            e.printStackTrace();
            System.err.println("\n💡 Kiểm tra lại:");
            System.err.println("  1. URL, username, password trong application.properties");
            System.err.println("  2. Firewall/Network có cho phép kết nối tới Aiven");
            System.err.println("  3. SSL configuration");
            System.err.println("========================================\n");
        }
        
        // Thoát sau khi test
        System.exit(0);
    }
}
