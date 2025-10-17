package demo.repository;

import demo.model.DonHang;
import demo.model.KhachHang;
import demo.model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for DonHang entity
 */
@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Long> {
    
    // Tìm đơn hàng theo khách hàng
    List<DonHang> findByKhachHang(KhachHang khachHang);
    
    // Tìm đơn hàng theo sản phẩm
    List<DonHang> findBySanPham(SanPham sanPham);
    
    // Tìm đơn hàng theo trạng thái
    List<DonHang> findByTrangthai(String trangthai);
    
    // Tìm đơn hàng trong khoảng thời gian
    List<DonHang> findByThoigianBetween(LocalDateTime start, LocalDateTime end);
    
    // Tìm đơn hàng theo khách hàng và trạng thái
    List<DonHang> findByKhachHangAndTrangthai(KhachHang khachHang, String trangthai);
    
    // Đếm số đơn hàng theo trạng thái
    long countByTrangthai(String trangthai);
    
    // Tính tổng doanh thu theo trạng thái
    @Query("SELECT SUM(d.tongtien) FROM DonHang d WHERE d.trangthai = :trangthai")
    Double sumTongtienByTrangthai(String trangthai);
    
    // Lấy đơn hàng gần nhất
    List<DonHang> findTop10ByOrderByThoigianDesc();
}
