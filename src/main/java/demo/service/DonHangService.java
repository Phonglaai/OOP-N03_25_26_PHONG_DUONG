package demo.service;

import demo.exception.MyException;
import demo.model.DonHang;
import demo.model.KhachHang;
import demo.model.SanPham;
import demo.repository.DonHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service for DonHang
 * Implements CRUD operations with business logic and exception handling
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
    public DonHang taoDonHang(Long khachHangId, Long sanPhamId, Integer soLuong) throws MyException {
        try {
            // Validate
            if (soLuong == null || soLuong <= 0) {
                throw new MyException("Số lượng phải lớn hơn 0");
            }
            
            // Lấy thông tin khách hàng và sản phẩm
            KhachHang khachHang = khachHangService.timKhachHangTheoId(khachHangId);
            SanPham sanPham = sanPhamService.timSanPhamTheoId(sanPhamId);
            
            // Kiểm tra sản phẩm còn hàng
            if (!sanPham.conHang()) {
                throw new MyException("Sản phẩm đã hết hàng: " + sanPham.getTenSanPham());
            }
            if (!sanPham.duSoLuong(soLuong)) {
                throw new MyException("Không đủ số lượng. Còn lại: " + sanPham.getSoLuongTonKho());
            }
            
            // Tạo đơn hàng
            DonHang donHang = new DonHang(khachHang, sanPham, soLuong);
            DonHang saved = donHangRepository.save(donHang);
            
            // Giảm tồn kho
            sanPhamService.capNhatTonKho(sanPhamId, -soLuong);
            
            return saved;
        } catch (MyException e) {
            throw e;
        } catch (Exception e) {
            throw new MyException("Lỗi khi tạo đơn hàng: " + e.getMessage(), e);
        }
    }

    /**
     * READ - Lấy tất cả đơn hàng
     */
    public List<DonHang> layTatCaDonHang() {
        return donHangRepository.findAll();
    }

    /**
     * READ - Tìm đơn hàng theo ID
     */
    public DonHang timDonHangTheoId(Long id) throws MyException {
        return donHangRepository.findById(id)
                .orElseThrow(() -> new MyException("Không tìm thấy đơn hàng với ID: " + id));
    }

    /**
     * READ - Tìm đơn hàng theo mã
     */
    public DonHang timDonHangTheoMa(String maDonHang) throws MyException {
        return donHangRepository.findByMaDonHang(maDonHang)
                .orElseThrow(() -> new MyException("Không tìm thấy đơn hàng với mã: " + maDonHang));
    }

    /**
     * READ - Lấy đơn hàng của khách hàng
     */
    public List<DonHang> layDonHangCuaKhachHang(Long khachHangId) throws MyException {
        KhachHang khachHang = khachHangService.timKhachHangTheoId(khachHangId);
        return donHangRepository.findByKhachHang(khachHang);
    }

    /**
     * READ - Lấy đơn hàng theo trạng thái
     */
    public List<DonHang> layDonHangTheoTrangThai(String trangThai) {
        return donHangRepository.findByTrangThai(trangThai);
    }

    /**
     * READ - Lấy đơn hàng trong khoảng thời gian
     */
    public List<DonHang> layDonHangTheoKhoangThoiGian(LocalDateTime tuNgay, LocalDateTime denNgay) {
        return donHangRepository.findByNgayDatBetween(tuNgay, denNgay);
    }

    /**
     * UPDATE - Cập nhật trạng thái đơn hàng
     */
    public DonHang capNhatTrangThai(Long id, String trangThaiMoi) throws MyException {
        try {
            DonHang donHang = timDonHangTheoId(id);
            
            // Validate trạng thái
            if (!isValidTrangThai(trangThaiMoi)) {
                throw new MyException("Trạng thái không hợp lệ: " + trangThaiMoi);
            }
            
            String trangThaiCu = donHang.getTrangThai();
            donHang.setTrangThai(trangThaiMoi);
            
            // Nếu hủy đơn, hoàn lại tồn kho
            if ("Đã hủy".equals(trangThaiMoi) && !"Đã hủy".equals(trangThaiCu)) {
                sanPhamService.capNhatTonKho(donHang.getSanPham().getId(), donHang.getSoLuong());
            }
            
            return donHangRepository.save(donHang);
        } catch (MyException e) {
            throw e;
        } catch (Exception e) {
            throw new MyException("Lỗi khi cập nhật trạng thái đơn hàng: " + e.getMessage(), e);
        }
    }

    /**
     * UPDATE - Hoàn thành đơn hàng
     */
    public DonHang hoanThanhDonHang(Long id) throws MyException {
        return capNhatTrangThai(id, "Đã hoàn thành");
    }

    /**
     * DELETE - Hủy đơn hàng
     */
    public DonHang huyDonHang(Long id) throws MyException {
        return capNhatTrangThai(id, "Đã hủy");
    }

    /**
     * Validate trạng thái
     */
    private boolean isValidTrangThai(String trangThai) {
        return "Đang xử lý".equals(trangThai) || 
               "Đã hoàn thành".equals(trangThai) || 
               "Đã hủy".equals(trangThai);
    }

    /**
     * Tính tổng doanh thu
     */
    public Double tinhTongDoanhThu() {
        Double total = donHangRepository.tinhTongDoanhThu();
        return total != null ? total : 0.0;
    }

    /**
     * Tính doanh thu hôm nay
     */
    public Double tinhDoanhThuHomNay() {
        Double today = donHangRepository.tinhDoanhThuHomNay();
        return today != null ? today : 0.0;
    }

    /**
     * Đếm đơn hàng hôm nay
     */
    public long demDonHangHomNay() {
        return donHangRepository.demDonHangHomNay();
    }

    /**
     * Đếm đơn hàng theo trạng thái
     */
    public long demDonHangTheoTrangThai(String trangThai) {
        return donHangRepository.countByTrangThai(trangThai);
    }
}
