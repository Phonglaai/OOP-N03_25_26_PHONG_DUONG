package demo.repository;

import demo.model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for SanPham entity
 */
@Repository
public interface SanPhamRespository extends JpaRepository<SanPham, Long> {
    
    // Tìm sản phẩm theo tên (tìm kiếm gần đúng)
    List<SanPham> findByTensanphamContainingIgnoreCase(String tensanpham);
    
    // Tìm sản phẩm còn hàng
    List<SanPham> findBySoluongGreaterThan(Integer soluong);
    
    // Tìm sản phẩm theo khoảng giá
    List<SanPham> findByDongiaBetween(Double minPrice, Double maxPrice);
    
    // Tìm sản phẩm sắp hết hàng
    @Query("SELECT s FROM SanPham s WHERE s.soluong <= :threshold ORDER BY s.soluong ASC")
    List<SanPham> findLowStockProducts(Integer threshold);
    
    // Lấy top sản phẩm theo giá
    List<SanPham> findTop10ByOrderByDongiaDesc();
}
