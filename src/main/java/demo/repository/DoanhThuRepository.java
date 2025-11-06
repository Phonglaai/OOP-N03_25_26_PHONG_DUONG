package demo.repository;

import demo.model.DoanhThu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository for DoanhThu entity
 * Provides CRUD operations and custom queries
 */
@Repository
public interface DoanhThuRepository extends JpaRepository<DoanhThu, Long> {

    /**
     * Tìm doanh thu theo ngày
     */
    Optional<DoanhThu> findByNgayGhiNhan(LocalDate ngayGhiNhan);

    /**
     * Tìm doanh thu trong khoảng thời gian
     */
    List<DoanhThu> findByNgayGhiNhanBetweenOrderByNgayGhiNhanDesc(LocalDate tuNgay, LocalDate denNgay);

    /**
     * Tìm doanh thu theo tháng
     */
    @Query("SELECT d FROM DoanhThu d WHERE MONTH(d.ngayGhiNhan) = :thang AND YEAR(d.ngayGhiNhan) = :nam ORDER BY d.ngayGhiNhan DESC")
    List<DoanhThu> findByThangNam(int thang, int nam);

    /**
     * Tính tổng doanh thu trong khoảng thời gian
     */
    @Query("SELECT SUM(d.tongTien) FROM DoanhThu d WHERE d.ngayGhiNhan BETWEEN :tuNgay AND :denNgay")
    Double tinhTongDoanhThu(LocalDate tuNgay, LocalDate denNgay);

    /**
     * Tính tổng đơn hàng trong khoảng thời gian
     */
    @Query("SELECT SUM(d.soDonHang) FROM DoanhThu d WHERE d.ngayGhiNhan BETWEEN :tuNgay AND :denNgay")
    Integer tinhTongDonHang(LocalDate tuNgay, LocalDate denNgay);

    /**
     * Lấy top ngày có doanh thu cao nhất
     */
    List<DoanhThu> findTop10ByOrderByTongTienDesc();

    /**
     * Kiểm tra đã có doanh thu trong ngày chưa
     */
    boolean existsByNgayGhiNhan(LocalDate ngayGhiNhan);

    /**
     * Lấy doanh thu hôm nay
     */
    @Query("SELECT d FROM DoanhThu d WHERE d.ngayGhiNhan = CURRENT_DATE")
    Optional<DoanhThu> layDoanhThuHomNay();
}
