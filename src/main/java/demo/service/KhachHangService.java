package demo.service;

import demo.exception.MyException;
import demo.model.KhachHang;
import demo.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for KhachHang
 * Implements CRUD operations with business logic and exception handling
 */
@Service
@Transactional
public class KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    /**
     * CREATE - Tạo khách hàng mới
     */
    public KhachHang taoKhachHang(KhachHang khachHang) throws MyException {
        try {
            // Validate
            if (khachHang.getTen() == null || khachHang.getTen().trim().isEmpty()) {
                throw new MyException("Tên khách hàng không được để trống");
            }
            if (khachHang.getSoDienThoai() == null || khachHang.getSoDienThoai().trim().isEmpty()) {
                throw new MyException("Số điện thoại không được để trống");
            }
            
            // Kiểm tra số điện thoại đã tồn tại
            if (khachHangRepository.existsBySoDienThoai(khachHang.getSoDienThoai())) {
                throw new MyException("Số điện thoại đã được đăng ký: " + khachHang.getSoDienThoai());
            }
            
            return khachHangRepository.save(khachHang);
        } catch (MyException e) {
            throw e;
        } catch (Exception e) {
            throw new MyException("Lỗi khi tạo khách hàng: " + e.getMessage(), e);
        }
    }

    /**
     * READ - Lấy tất cả khách hàng
     */
    public List<KhachHang> layTatCaKhachHang() {
        return khachHangRepository.findAll();
    }

    /**
     * READ - Tìm khách hàng theo ID
     */
    public KhachHang timKhachHangTheoId(Long id) throws MyException {
        return khachHangRepository.findById(id)
                .orElseThrow(() -> new MyException("Không tìm thấy khách hàng với ID: " + id));
    }

    /**
     * READ - Tìm khách hàng theo số điện thoại
     */
    public KhachHang timKhachHangTheoSoDienThoai(String soDienThoai) throws MyException {
        return khachHangRepository.findBySoDienThoai(soDienThoai)
                .orElseThrow(() -> new MyException("Không tìm thấy khách hàng với số điện thoại: " + soDienThoai));
    }

    /**
     * READ - Tìm khách hàng theo tên
     */
    public List<KhachHang> timKhachHangTheoTen(String ten) {
        return khachHangRepository.findByTenContainingIgnoreCase(ten);
    }

    /**
     * UPDATE - Cập nhật thông tin khách hàng
     */
    public KhachHang capNhatKhachHang(Long id, KhachHang khachHangMoi) throws MyException {
        try {
            KhachHang khachHangCu = timKhachHangTheoId(id);
            
            // Cập nhật thông tin
            if (khachHangMoi.getTen() != null && !khachHangMoi.getTen().trim().isEmpty()) {
                khachHangCu.setTen(khachHangMoi.getTen());
            }
            if (khachHangMoi.getDiaChi() != null) {
                khachHangCu.setDiaChi(khachHangMoi.getDiaChi());
            }
            if (khachHangMoi.getEmail() != null) {
                khachHangCu.setEmail(khachHangMoi.getEmail());
            }
            // Không cho phép thay đổi số điện thoại
            
            return khachHangRepository.save(khachHangCu);
        } catch (MyException e) {
            throw e;
        } catch (Exception e) {
            throw new MyException("Lỗi khi cập nhật khách hàng: " + e.getMessage(), e);
        }
    }

    /**
     * DELETE - Xóa khách hàng
     */
    public void xoaKhachHang(Long id) throws MyException {
        try {
            KhachHang khachHang = timKhachHangTheoId(id);
            
            // Kiểm tra có đơn hàng không
            if (khachHang.getDanhSachDonHang() != null && !khachHang.getDanhSachDonHang().isEmpty()) {
                throw new MyException("Không thể xóa khách hàng đã có đơn hàng. ID: " + id);
            }
            
            khachHangRepository.deleteById(id);
        } catch (MyException e) {
            throw e;
        } catch (Exception e) {
            throw new MyException("Lỗi khi xóa khách hàng: " + e.getMessage(), e);
        }
    }

    /**
     * Đếm tổng số khách hàng
     */
    public long demTongSoKhachHang() {
        return khachHangRepository.count();
    }

    /**
     * Lấy danh sách khách hàng tích cực
     */
    public List<KhachHang> layKhachHangTichCuc() {
        return khachHangRepository.timKhachHangTichCuc();
    }
}
