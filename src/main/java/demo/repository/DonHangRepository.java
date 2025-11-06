package demo.repository;

import demo.model.DonHang;
import demo.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository for DonHang entity
 * Provides CRUD operations and custom queries
 */
@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Long> {

    /**
     * Tìm đơn hàng theo mã
     */
    Optional<DonHang> findByMaDonHang(String maDonHang);

    /**
     * Tìm đơn hàng theo khách hàng
     */
    List<DonHang> findByKhachHang(KhachHang khachHang);

    /**
     * Tìm đơn hàng theo trạng thái
     */
    List<DonHang> findByTrangThai(String trangThai);

    /**
     * Tìm đơn hàng trong khoảng thời gian
     */
    List<DonHang> findByNgayDatBetween(LocalDateTime tuNgay, LocalDateTime denNgay);

    /**
     * Tìm đơn hàng của khách hàng theo trạng thái
     */
    List<DonHang> findByKhachHangAndTrangThai(KhachHang khachHang, String trangThai);

    /**
     * Đếm số đơn hàng theo trạng thái
     */
    long countByTrangThai(String trangThai);

    /**
     * Tính tổng doanh thu đã hoàn thành
     */
    @Query("SELECT SUM(d.tongTien) FROM DonHang d WHERE d.trangThai = 'Đã hoàn thành'")
    Double tinhTongDoanhThu();

    /**
     * Tính tổng doanh thu theo ngày
     */
    @Query("SELECT SUM(d.tongTien) FROM DonHang d WHERE CAST(d.ngayDat AS date) = CURRENT_DATE AND d.trangThai = 'Đã hoàn thành'")
    Double tinhDoanhThuHomNay();

    /**
     * Đếm số đơn hàng hoàn thành hôm nay
     */
    @Query("SELECT COUNT(d) FROM DonHang d WHERE CAST(d.ngayDat AS date) = CURRENT_DATE AND d.trangThai = 'Đã hoàn thành'")
    long demDonHangHomNay();
}
