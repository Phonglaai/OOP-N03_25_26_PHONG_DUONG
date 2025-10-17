package demo.repository;

import demo.model.DoanhThu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for DoanhThu entity
 */
@Repository
public interface DoanhThuRepository extends JpaRepository<DoanhThu, Long> {
    
    // Tìm doanh thu theo ngày
    Optional<DoanhThu> findByThoigian(LocalDate thoigian);
    
    // Tìm doanh thu trong khoảng thời gian
    List<DoanhThu> findByThoigianBetweenOrderByThoigianDesc(LocalDate start, LocalDate end);
    
    // Tính tổng doanh thu trong khoảng thời gian
    @Query("SELECT SUM(d.tongtien) FROM DoanhThu d WHERE d.thoigian BETWEEN :start AND :end")
    Double sumTongtienBetweenDates(LocalDate start, LocalDate end);
    
    // Đếm số đơn trong khoảng thời gian
    @Query("SELECT SUM(d.sodon) FROM DoanhThu d WHERE d.thoigian BETWEEN :start AND :end")
    Long sumSodonBetweenDates(LocalDate start, LocalDate end);
    
    // Lấy doanh thu cao nhất
    List<DoanhThu> findTop10ByOrderByTongtienDesc();
    
    // Kiểm tra doanh thu đã tồn tại theo ngày
    boolean existsByThoigian(LocalDate thoigian);
}
