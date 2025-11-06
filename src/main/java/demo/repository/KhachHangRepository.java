package demo.repository;

import demo.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for KhachHang entity
 * Provides CRUD operations and custom queries
 */
@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {

    /**
     * Tìm khách hàng theo số điện thoại
     */
    Optional<KhachHang> findBySoDienThoai(String soDienThoai);

    /**
     * Tìm khách hàng theo tên (chứa keyword)
     */
    List<KhachHang> findByTenContainingIgnoreCase(String ten);

    /**
     * Tìm khách hàng theo email
     */
    Optional<KhachHang> findByEmail(String email);

    /**
     * Kiểm tra số điện thoại đã tồn tại
     */
    boolean existsBySoDienThoai(String soDienThoai);

    /**
     * Đếm tổng số khách hàng
     */
    @Query("SELECT COUNT(k) FROM KhachHang k")
    long demTongSoKhachHang();

    /**
     * Lấy danh sách khách hàng có nhiều đơn hàng nhất
     */
    @Query("SELECT k FROM KhachHang k LEFT JOIN k.danhSachDonHang d GROUP BY k ORDER BY COUNT(d) DESC")
    List<KhachHang> timKhachHangTichCuc();
}
