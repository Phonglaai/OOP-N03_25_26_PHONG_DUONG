package demo.service;

import demo.exception.MyException;
import demo.model.DoanhThu;
import demo.model.DonHang;
import demo.repository.DoanhThuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service for DoanhThu
 * Implements CRUD operations with business logic and exception handling
 * ĐÂY LÀ SERVICE CHÍNH - LƯU GIỮ HOẠT ĐỘNG CHÍNH CỦA ỨNG DỤNG
 */
@Service
@Transactional
public class DoanhThuService {

    @Autowired
    private DoanhThuRepository doanhThuRepository;

    @Autowired
    private DonHangService donHangService;

    /**
     * CREATE - Tạo báo cáo doanh thu mới
     */
    public DoanhThu taoDoanhThu(LocalDate ngayGhiNhan) throws MyException {
        try {
            // Kiểm tra đã có doanh thu ngày này chưa
            if (doanhThuRepository.existsByNgayGhiNhan(ngayGhiNhan)) {
                throw new MyException("Đã tồn tại báo cáo doanh thu ngày: " + ngayGhiNhan);
            }
            
            DoanhThu doanhThu = new DoanhThu(ngayGhiNhan);
            return doanhThuRepository.save(doanhThu);
        } catch (MyException e) {
            throw e;
        } catch (Exception e) {
            throw new MyException("Lỗi khi tạo báo cáo doanh thu: " + e.getMessage(), e);
        }
    }

    /**
     * READ - Lấy tất cả báo cáo doanh thu
     */
    public List<DoanhThu> layTatCaDoanhThu() {
        return doanhThuRepository.findAll();
    }

    /**
     * READ - Tìm doanh thu theo ID
     */
    public DoanhThu timDoanhThuTheoId(Long id) throws MyException {
        return doanhThuRepository.findById(id)
                .orElseThrow(() -> new MyException("Không tìm thấy doanh thu với ID: " + id));
    }

    /**
     * READ - Lấy doanh thu theo ngày
     */
    public DoanhThu layDoanhThuTheoNgay(LocalDate ngay) throws MyException {
        return doanhThuRepository.findByNgayGhiNhan(ngay)
                .orElseThrow(() -> new MyException("Không tìm thấy doanh thu ngày: " + ngay));
    }

    /**
     * READ - Lấy doanh thu hôm nay
     */
    public DoanhThu layDoanhThuHomNay() throws MyException {
        return doanhThuRepository.layDoanhThuHomNay()
                .orElseGet(() -> {
                    try {
                        return taoDoanhThu(LocalDate.now());
                    } catch (MyException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    /**
     * READ - Lấy doanh thu trong khoảng thời gian
     */
    public List<DoanhThu> layDoanhThuTheoKhoangThoiGian(LocalDate tuNgay, LocalDate denNgay) {
        return doanhThuRepository.findByNgayGhiNhanBetweenOrderByNgayGhiNhanDesc(tuNgay, denNgay);
    }

    /**
     * UPDATE - Cập nhật doanh thu
     * PHƯƠNG THỨC HOẠT ĐỘNG CHÍNH CỦA ỨNG DỤNG
     */
    public DoanhThu capNhatDoanhThu(Long id, Double tongTien, Integer soDonHang, Integer soKhachHang) throws MyException {
        try {
            DoanhThu doanhThu = timDoanhThuTheoId(id);
            
            if (tongTien != null && tongTien >= 0) {
                doanhThu.setTongTien(tongTien);
            }
            if (soDonHang != null && soDonHang >= 0) {
                doanhThu.setSoDonHang(soDonHang);
            }
            if (soKhachHang != null && soKhachHang >= 0) {
                doanhThu.setSoKhachHang(soKhachHang);
            }
            
            doanhThu.setThoiGianCapNhat(LocalDateTime.now());
            return doanhThuRepository.save(doanhThu);
        } catch (MyException e) {
            throw e;
        } catch (Exception e) {
            throw new MyException("Lỗi khi cập nhật doanh thu: " + e.getMessage(), e);
        }
    }

    /**
     * UPDATE - Cập nhật doanh thu từ đơn hàng (CORE FUNCTION)
     * Đây là phương thức chính liên kết các đối tượng: DoanhThu, DonHang, KhachHang, SanPham
     */
    public DoanhThu capNhatDoanhThuTuDonHang(DonHang donHang) throws MyException {
        try {
            if (donHang == null || !donHang.daHoanThanh()) {
                throw new MyException("Đơn hàng chưa hoàn thành hoặc không hợp lệ");
            }
            
            LocalDate ngayDonHang = donHang.getNgayDat().toLocalDate();
            
            // Lấy hoặc tạo doanh thu cho ngày đó
            DoanhThu doanhThu;
            if (doanhThuRepository.existsByNgayGhiNhan(ngayDonHang)) {
                doanhThu = layDoanhThuTheoNgay(ngayDonHang);
            } else {
                doanhThu = taoDoanhThu(ngayDonHang);
            }
            
            // Cập nhật doanh thu từ đơn hàng
            doanhThu.capNhatDoanhThuTuDonHang(donHang);
            
            return doanhThuRepository.save(doanhThu);
        } catch (MyException e) {
            throw e;
        } catch (Exception e) {
            throw new MyException("Lỗi khi cập nhật doanh thu từ đơn hàng: " + e.getMessage(), e);
        }
    }

    /**
     * DELETE - Xóa báo cáo doanh thu
     */
    public void xoaDoanhThu(Long id) throws MyException {
        try {
            DoanhThu doanhThu = timDoanhThuTheoId(id);
            doanhThuRepository.deleteById(id);
        } catch (MyException e) {
            throw e;
        } catch (Exception e) {
            throw new MyException("Lỗi khi xóa doanh thu: " + e.getMessage(), e);
        }
    }

    /**
     * Tính tổng doanh thu trong khoảng thời gian
     */
    public Double tinhTongDoanhThuKhoangThoiGian(LocalDate tuNgay, LocalDate denNgay) {
        Double total = doanhThuRepository.tinhTongDoanhThu(tuNgay, denNgay);
        return total != null ? total : 0.0;
    }

    /**
     * Tính tổng đơn hàng trong khoảng thời gian
     */
    public Integer tinhTongDonHangKhoangThoiGian(LocalDate tuNgay, LocalDate denNgay) {
        Integer total = doanhThuRepository.tinhTongDonHang(tuNgay, denNgay);
        return total != null ? total : 0;
    }

    /**
     * Lấy top doanh thu cao nhất
     */
    public List<DoanhThu> layTopDoanhThuCaoNhat() {
        return doanhThuRepository.findTop10ByOrderByTongTienDesc();
    }

    /**
     * Đồng bộ doanh thu hôm nay từ đơn hàng
     * PHƯƠNG THỨC CHÍNH TỰ ĐỘNG TÍNH TOÁN DOANH THU
     */
    public DoanhThu dongBoDoanhThuHomNay() throws MyException {
        try {
            LocalDate today = LocalDate.now();
            
            // Lấy hoặc tạo doanh thu hôm nay
            DoanhThu doanhThu;
            if (doanhThuRepository.existsByNgayGhiNhan(today)) {
                doanhThu = layDoanhThuTheoNgay(today);
            } else {
                doanhThu = taoDoanhThu(today);
            }
            
            // Reset và tính lại từ đơn hàng
            doanhThu.resetDoanhThu();
            
            Double tongTien = donHangService.tinhDoanhThuHomNay();
            long soDonHang = donHangService.demDonHangHomNay();
            
            doanhThu.setTongTien(tongTien);
            doanhThu.setSoDonHang((int) soDonHang);
            doanhThu.setThoiGianCapNhat(LocalDateTime.now());
            
            return doanhThuRepository.save(doanhThu);
        } catch (Exception e) {
            throw new MyException("Lỗi khi đồng bộ doanh thu hôm nay: " + e.getMessage(), e);
        }
    }
}
