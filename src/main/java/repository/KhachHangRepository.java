package demo.repository;

import demo.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for KhachHang entity
 */
@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {
    
    // Tìm khách hàng theo số điện thoại
    Optional<KhachHang> findBySodienthoai(String sodienthoai);
    
    // Tìm khách hàng theo tên (tìm kiếm gần đúng)
    List<KhachHang> findByTenContainingIgnoreCase(String ten);
    
    // Kiểm tra số điện thoại đã tồn tại
    boolean existsBySodienthoai(String sodienthoai);
    
    // Đếm tổng số khách hàng
    long count();
}
