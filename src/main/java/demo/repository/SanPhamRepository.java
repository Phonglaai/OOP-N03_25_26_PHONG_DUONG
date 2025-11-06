package demo.repository;

import demo.model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for SanPham entity
 * Provides CRUD operations and custom queries
 */
@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    /**
     * Tìm sản phẩm theo mã
     */
    Optional<SanPham> findByMaSanPham(String maSanPham);

    /**
     * Tìm sản phẩm theo tên (chứa keyword)
     */
    List<SanPham> findByTenSanPhamContainingIgnoreCase(String tenSanPham);

    /**
     * Tìm sản phẩm theo loại
     */
    List<SanPham> findByLoaiSanPham(String loaiSanPham);

    /**
     * Tìm sản phẩm còn bán
     */
    List<SanPham> findByTrangThaiTrue();

    /**
     * Tìm sản phẩm trong khoảng giá
     */
    List<SanPham> findByDonGiaBetween(Double minPrice, Double maxPrice);

    /**
     * Tìm sản phẩm còn hàng
     */
    @Query("SELECT s FROM SanPham s WHERE s.soLuongTonKho > 0 AND s.trangThai = true")
    List<SanPham> timSanPhamConHang();

    /**
     * Tìm sản phẩm sắp hết hàng (dưới ngưỡng)
     */
    @Query("SELECT s FROM SanPham s WHERE s.soLuongTonKho < :nguong AND s.trangThai = true")
    List<SanPham> timSanPhamSapHetHang(int nguong);

    /**
     * Đếm số lượng sản phẩm còn bán
     */
    long countByTrangThaiTrue();
}
