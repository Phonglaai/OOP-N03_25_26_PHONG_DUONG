package demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Database Connection Configuration for Aiven MySQL
 * Cấu hình kết nối cơ sở dữ liệu MySQL trên Aiven
 */
@Configuration
public class DatabaseConnection {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    /**
     * Cấu hình DataSource với HikariCP connection pool
     * @return DataSource được cấu hình với các thông số tối ưu
     */
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        
        // Thông tin kết nối cơ bản
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driverClassName);
        
        // Cấu hình Connection Pool
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(30000); // 30 seconds
        config.setIdleTimeout(600000); // 10 minutes
        config.setMaxLifetime(1800000); // 30 minutes
        
        // Cấu hình SSL cho Aiven MySQL
        config.addDataSourceProperty("useSSL", "true");
        config.addDataSourceProperty("requireSSL", "true");
        config.addDataSourceProperty("verifyServerCertificate", "false");
        
        // Cấu hình tối ưu cho MySQL
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        
        // Tên pool để dễ theo dõi trong logs
        config.setPoolName("AivenMySQLPool");
        
        return new HikariDataSource(config);
    }
}
