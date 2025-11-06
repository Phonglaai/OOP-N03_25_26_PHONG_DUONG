package demo.service;

import demo.exception.MyException;
import demo.model.SanPham;
import demo.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for SanPham
 * Implements CRUD operations with business logic and exception handling
 */
@Service
@Transactional
public class SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    /**
     * CREATE - Thêm sản phẩm mới
     */
    public SanPham themSanPham(SanPham sanPham) throws MyException {
        try {
            // Validate
            if (sanPham.getMaSanPham() == null || sanPham.getMaSanPham().trim().isEmpty()) {
                throw new MyException("Mã sản phẩm không được để trống");
            }
            if (sanPham.getTenSanPham() == null || sanPham.getTenSanPham().trim().isEmpty()) {
                throw new MyException("Tên sản phẩm không được để trống");
            }
            if (sanPham.getDonGia() == null || sanPham.getDonGia() <= 0) {
                throw new MyException("Đơn giá phải lớn hơn 0");
            }
            
            // Kiểm tra mã sản phẩm đã tồn tại
            if (sanPhamRepository.findByMaSanPham(sanPham.getMaSanPham()).isPresent()) {
                throw new MyException("Mã sản phẩm đã tồn tại: " + sanPham.getMaSanPham());
            }
            
            return sanPhamRepository.save(sanPham);
        } catch (MyException e) {
            throw e;
        } catch (Exception e) {
            throw new MyException("Lỗi khi thêm sản phẩm: " + e.getMessage(), e);
        }
    }

    /**
     * READ - Lấy tất cả sản phẩm
     */
    public List<SanPham> layTatCaSanPham() {
        return sanPhamRepository.findAll();
    }

    /**
     * READ - Lấy sản phẩm còn bán
     */
    public List<SanPham> laySanPhamConBan() {
        return sanPhamRepository.findByTrangThaiTrue();
    }

    /**
     * READ - Tìm sản phẩm theo ID
     */
    public SanPham timSanPhamTheoId(Long id) throws MyException {
        return sanPhamRepository.findById(id)
                .orElseThrow(() -> new MyException("Không tìm thấy sản phẩm với ID: " + id));
    }

    /**
     * READ - Tìm sản phẩm theo mã
     */
    public SanPham timSanPhamTheoMa(String maSanPham) throws MyException {
        return sanPhamRepository.findByMaSanPham(maSanPham)
                .orElseThrow(() -> new MyException("Không tìm thấy sản phẩm với mã: " + maSanPham));
    }

    /**
     * READ - Tìm sản phẩm theo tên
     */
    public List<SanPham> timSanPhamTheoTen(String tenSanPham) {
        return sanPhamRepository.findByTenSanPhamContainingIgnoreCase(tenSanPham);
    }

    /**
     * READ - Tìm sản phẩm theo loại
     */
    public List<SanPham> timSanPhamTheoLoai(String loaiSanPham) {
        return sanPhamRepository.findByLoaiSanPham(loaiSanPham);
    }

    /**
     * UPDATE - Cập nhật thông tin sản phẩm
     */
    public SanPham capNhatSanPham(Long id, SanPham sanPhamMoi) throws MyException {
        try {
            SanPham sanPhamCu = timSanPhamTheoId(id);
            
            // Cập nhật thông tin
            if (sanPhamMoi.getTenSanPham() != null && !sanPhamMoi.getTenSanPham().trim().isEmpty()) {
                sanPhamCu.setTenSanPham(sanPhamMoi.getTenSanPham());
            }
            if (sanPhamMoi.getDonGia() != null && sanPhamMoi.getDonGia() > 0) {
                sanPhamCu.setDonGia(sanPhamMoi.getDonGia());
            }
            if (sanPhamMoi.getSoLuongTonKho() != null) {
                sanPhamCu.setSoLuongTonKho(sanPhamMoi.getSoLuongTonKho());
            }
            if (sanPhamMoi.getMoTa() != null) {
                sanPhamCu.setMoTa(sanPhamMoi.getMoTa());
            }
            if (sanPhamMoi.getLoaiSanPham() != null) {
                sanPhamCu.setLoaiSanPham(sanPhamMoi.getLoaiSanPham());
            }
            if (sanPhamMoi.getTrangThai() != null) {
                sanPhamCu.setTrangThai(sanPhamMoi.getTrangThai());
            }
            
            return sanPhamRepository.save(sanPhamCu);
        } catch (MyException e) {
            throw e;
        } catch (Exception e) {
            throw new MyException("Lỗi khi cập nhật sản phẩm: " + e.getMessage(), e);
        }
    }

    /**
     * DELETE - Xóa sản phẩm
     */
    public void xoaSanPham(Long id) throws MyException {
        try {
            SanPham sanPham = timSanPhamTheoId(id);
            
            // Thay vì xóa hẳn, set trạng thái = false (ngừng bán)
            sanPham.setTrangThai(false);
            sanPhamRepository.save(sanPham);
        } catch (MyException e) {
            throw e;
        } catch (Exception e) {
            throw new MyException("Lỗi khi xóa sản phẩm: " + e.getMessage(), e);
        }
    }

    /**
     * Cập nhật tồn kho
     */
    public SanPham capNhatTonKho(Long id, int soLuongThayDoi) throws MyException {
        try {
            SanPham sanPham = timSanPhamTheoId(id);
            
            int tonKhoMoi = (sanPham.getSoLuongTonKho() != null ? sanPham.getSoLuongTonKho() : 0) + soLuongThayDoi;
            if (tonKhoMoi < 0) {
                throw new MyException("Số lượng tồn kho không thể âm");
            }
            
            sanPham.setSoLuongTonKho(tonKhoMoi);
            return sanPhamRepository.save(sanPham);
        } catch (MyException e) {
            throw e;
        } catch (Exception e) {
            throw new MyException("Lỗi khi cập nhật tồn kho: " + e.getMessage(), e);
        }
    }

    /**
     * Kiểm tra sản phẩm còn hàng
     */
    public boolean kiemTraConHang(Long id) throws MyException {
        SanPham sanPham = timSanPhamTheoId(id);
        return sanPham.conHang();
    }

    /**
     * Đếm số lượng sản phẩm còn bán
     */
    public long demSoLuongSanPhamConBan() {
        return sanPhamRepository.countByTrangThaiTrue();
    }
}
