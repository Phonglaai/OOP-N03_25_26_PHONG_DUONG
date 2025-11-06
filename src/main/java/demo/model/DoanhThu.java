package demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity DoanhThu - Quản lý doanh thu theo ngày
 * Đây là đối tượng chính lưu giữ hoạt động chính của ứng dụng
 */
@Entity
@Table(name = "doanh_thu")
public class DoanhThu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ngay_ghi_nhan", nullable = false, unique = true)
    private LocalDate ngayGhiNhan;

    @Column(name = "tong_tien", nullable = false)
    private Double tongTien;

    @Column(name = "so_don_hang", nullable = false)
    private Integer soDonHang;

    @Column(name = "so_khach_hang")
    private Integer soKhachHang;

    @Column(name = "thoi_gian_cap_nhat")
    private LocalDateTime thoiGianCapNhat;

    @Column(name = "ghi_chu", length = 500)
    private String ghiChu;

    // Constructors
    public DoanhThu() {
        this.ngayGhiNhan = LocalDate.now();
        this.tongTien = 0.0;
        this.soDonHang = 0;
        this.soKhachHang = 0;
        this.thoiGianCapNhat = LocalDateTime.now();
    }

    public DoanhThu(LocalDate ngayGhiNhan) {
        this.ngayGhiNhan = ngayGhiNhan;
        this.tongTien = 0.0;
        this.soDonHang = 0;
        this.soKhachHang = 0;
        this.thoiGianCapNhat = LocalDateTime.now();
    }

    public DoanhThu(LocalDate ngayGhiNhan, Double tongTien, Integer soDonHang, Integer soKhachHang) {
        this.ngayGhiNhan = ngayGhiNhan;
        this.tongTien = tongTien;
        this.soDonHang = soDonHang;
        this.soKhachHang = soKhachHang;
        this.thoiGianCapNhat = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getNgayGhiNhan() {
        return ngayGhiNhan;
    }

    public void setNgayGhiNhan(LocalDate ngayGhiNhan) {
        this.ngayGhiNhan = ngayGhiNhan;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
        this.thoiGianCapNhat = LocalDateTime.now();
    }

    public Integer getSoDonHang() {
        return soDonHang;
    }

    public void setSoDonHang(Integer soDonHang) {
        this.soDonHang = soDonHang;
        this.thoiGianCapNhat = LocalDateTime.now();
    }

    public Integer getSoKhachHang() {
        return soKhachHang;
    }

    public void setSoKhachHang(Integer soKhachHang) {
        this.soKhachHang = soKhachHang;
        this.thoiGianCapNhat = LocalDateTime.now();
    }

    public LocalDateTime getThoiGianCapNhat() {
        return thoiGianCapNhat;
    }

    public void setThoiGianCapNhat(LocalDateTime thoiGianCapNhat) {
        this.thoiGianCapNhat = thoiGianCapNhat;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    // Business methods - PHƯƠNG THỨC HOẠT ĐỘNG CHÍNH CỦA ỨNG DỤNG
    
    /**
     * Cập nhật doanh thu khi có đơn hàng mới
     * Đây là phương thức chính (core function) của ứng dụng
     */
    public void capNhatDoanhThuTuDonHang(DonHang donHang) {
        if (donHang != null && donHang.daHoanThanh()) {
            this.tongTien += donHang.getTongTien();
            this.soDonHang++;
            this.thoiGianCapNhat = LocalDateTime.now();
        }
    }

    /**
     * Tính doanh thu trung bình mỗi đơn
     */
    public double tinhDoanhThuTrungBinh() {
        if (soDonHang == null || soDonHang == 0) {
            return 0.0;
        }
        return tongTien / soDonHang;
    }

    /**
     * Thêm doanh thu
     */
    public void themDoanhThu(double soTien) {
        this.tongTien += soTien;
        this.thoiGianCapNhat = LocalDateTime.now();
    }

    /**
     * Thêm đơn hàng
     */
    public void themDonHang() {
        this.soDonHang++;
        this.thoiGianCapNhat = LocalDateTime.now();
    }

    /**
     * Reset doanh thu (đầu ngày mới)
     */
    public void resetDoanhThu() {
        this.tongTien = 0.0;
        this.soDonHang = 0;
        this.soKhachHang = 0;
        this.thoiGianCapNhat = LocalDateTime.now();
    }

    /**
     * Kiểm tra có doanh thu không
     */
    public boolean coDoanhThu() {
        return tongTien != null && tongTien > 0;
    }

    @Override
    public String toString() {
        return "DoanhThu{" +
                "id=" + id +
                ", ngayGhiNhan=" + ngayGhiNhan +
                ", tongTien=" + tongTien +
                ", soDonHang=" + soDonHang +
                ", soKhachHang=" + soKhachHang +
                ", thoiGianCapNhat=" + thoiGianCapNhat +
                '}';
    }
}
