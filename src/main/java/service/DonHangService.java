package demo.service;

import demo.model.DonHang;
import demo.model.KhachHang;
import demo.model.SanPham;
import demo.repository.DonHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for DonHang entity
 * Implements business logic and error handling
 */
@Service
@Transactional
public class DonHangService {
    
    @Autowired
    private DonHangRepository donHangRepository;
    
    @Autowired
    private KhachHangService khachHangService;
    
    @Autowired
    private SanPhamService sanPhamService;
    
    /**
     * CREATE - Tạo đơn hàng mới
     */
    public DonHang taoDonHang(DonHang donHang) {
        try {
            // Validate input
            if (donHang == null) {
                throw new IllegalArgumentException("Đơn hàng không được null");
            }
            
            if (donHang.getKhachHang() == null || donHang.getKhachHang().getStt() == null) {
                throw new IllegalArgumentException("Khách hàng không hợp lệ");
            }
            
            if (donHang.getSanPham() == null || donHang.getSanPham().getId() == null) {
                throw new IllegalArgumentException("Sản phẩm không hợp lệ");
            }
            
            if (donHang.getSoluong() == null || donHang.getSoluong() <= 0) {
                throw new IllegalArgumentException("Số lượng phải lớn hơn 0");
            }
            
            // Verify customer exists
            KhachHang khachHang = khachHangService.findById(donHang.getKhachHang().getStt())
                .orElseThrow(() -> new IllegalArgumentException("Khách hàng không tồn tại"));
            
            // Verify product exists and has enough stock
            SanPham sanPham = sanPhamService.findById(donHang.getSanPham().getId())
                .orElseThrow(() -> new IllegalArgumentException("Sản phẩm không tồn tại"));
            
            if (sanPham.getSoluong() < donHang.getSoluong()) {
                throw new IllegalArgumentException("Không đủ số lượng sản phẩm. Còn lại: " + sanPham.getSoluong());
            }
            
            // Set relationships
            donHang.setKhachHang(khachHang);
            donHang.setSanPham(sanPham);
            
            // Calculate total
            donHang.tinhTongTien();
            
            // Set timestamp if not set
            if (donHang.getThoigian() == null) {
                donHang.setThoigian(LocalDateTime.now());
            }
            
            // Set default status
            if (donHang.getTrangthai() == null || donHang.getTrangthai().isEmpty()) {
                donHang.setTrangthai("Đang xử lý");
            }
            
            // Update product stock
            sanPham.setSoluong(sanPham.getSoluong() - donHang.getSoluong());
            sanPhamService.save(sanPham);
            
            // Save order
            return donHangRepository.save(donHang);
            
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tạo đơn hàng: " + e.getMessage(), e);
        }
    }
    
    /**
     * READ - Lấy tất cả đơn hàng
     */
    public List<DonHang> getAllDonHang() {
        try {
            return donHangRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách đơn hàng: " + e.getMessage(), e);
        }
    }
    
    /**
     * READ - Tìm đơn hàng theo ID
     */
    public Optional<DonHang> findById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("ID không được null");
            }
            return donHangRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tìm đơn hàng: " + e.getMessage(), e);
        }
    }
    
    /**
     * READ - Lấy đơn hàng theo khách hàng
     */
    public List<DonHang> getDonHangByKhachHang(Long khachHangId) {
        try {
            KhachHang khachHang = khachHangService.findById(khachHangId)
                .orElseThrow(() -> new IllegalArgumentException("Khách hàng không tồn tại"));
            return donHangRepository.findByKhachHang(khachHang);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy đơn hàng theo khách hàng: " + e.getMessage(), e);
        }
    }
    
    /**
     * READ - Lấy đơn hàng theo trạng thái
     */
    public List<DonHang> getDonHangByTrangthai(String trangthai) {
        try {
            return donHangRepository.findByTrangthai(trangthai);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy đơn hàng theo trạng thái: " + e.getMessage(), e);
        }
    }
    
    /**
     * UPDATE - Cập nhật đơn hàng
     */
    public DonHang capNhatDonHang(Long id, DonHang donHangMoi) {
        try {
            DonHang donHangCu = donHangRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Đơn hàng không tồn tại"));
            
            // Update status
            if (donHangMoi.getTrangthai() != null && !donHangMoi.getTrangthai().isEmpty()) {
                donHangCu.setTrangthai(donHangMoi.getTrangthai());
            }
            
            // Update note
            if (donHangMoi.getGhichu() != null) {
                donHangCu.setGhichu(donHangMoi.getGhichu());
            }
            
            return donHangRepository.save(donHangCu);
            
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi cập nhật đơn hàng: " + e.getMessage(), e);
        }
    }
    
    /**
     * UPDATE - Cập nhật trạng thái đơn hàng
     */
    public DonHang capNhatTrangthai(Long id, String trangthaiMoi) {
        try {
            DonHang donHang = donHangRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Đơn hàng không tồn tại"));
            
            donHang.setTrangthai(trangthaiMoi);
            return donHangRepository.save(donHang);
            
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi cập nhật trạng thái: " + e.getMessage(), e);
        }
    }
    
    /**
     * DELETE - Xóa đơn hàng
     */
    public void xoaDonHang(Long id) {
        try {
            DonHang donHang = donHangRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Đơn hàng không tồn tại"));
            
            // Restore product stock if order is being deleted
            if (donHang.getTrangthai().equals("Đang xử lý") || donHang.getTrangthai().equals("Hoàn thành")) {
                SanPham sanPham = donHang.getSanPham();
                sanPham.setSoluong(sanPham.getSoluong() + donHang.getSoluong());
                sanPhamService.save(sanPham);
            }
            
            donHangRepository.deleteById(id);
            
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xóa đơn hàng: " + e.getMessage(), e);
        }
    }
    
    /**
     * Lấy 10 đơn hàng gần nhất
     */
    public List<DonHang> getRecentOrders() {
        try {
            return donHangRepository.findTop10ByOrderByThoigianDesc();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy đơn hàng gần nhất: " + e.getMessage(), e);
        }
    }
    
    /**
     * Đếm số đơn hàng theo trạng thái
     */
    public long countByTrangthai(String trangthai) {
        try {
            return donHangRepository.countByTrangthai(trangthai);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi đếm đơn hàng: " + e.getMessage(), e);
        }
    }
    
    /**
     * Tính tổng doanh thu theo trạng thái
     */
    public Double sumTongtienByTrangthai(String trangthai) {
        try {
            Double total = donHangRepository.sumTongtienByTrangthai(trangthai);
            return total != null ? total : 0.0;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tính tổng doanh thu: " + e.getMessage(), e);
        }
    }
}
