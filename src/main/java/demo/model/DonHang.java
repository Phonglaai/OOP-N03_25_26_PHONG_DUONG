package demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity DonHang - Quản lý đơn hàng
 */
@Entity
@Table(name = "don_hang")
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_don_hang", nullable = false, unique = true, length = 50)
    private String maDonHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khach_hang_id", nullable = false)
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "san_pham_id", nullable = false)
    private SanPham sanPham;

    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

    @Column(name = "don_gia")
    private Double donGia;

    @Column(name = "tong_tien", nullable = false)
    private Double tongTien;

    @Column(name = "ngay_dat")
    private LocalDateTime ngayDat;

    @Column(name = "trang_thai", length = 50)
    private String trangThai; // "Đang xử lý", "Đã hoàn thành", "Đã hủy"

    @Column(name = "ghi_chu", length = 500)
    private String ghiChu;

    // Constructors
    public DonHang() {
        this.ngayDat = LocalDateTime.now();
        this.trangThai = "Đang xử lý";
    }

    public DonHang(KhachHang khachHang, SanPham sanPham, Integer soLuong) {
        this.khachHang = khachHang;
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.donGia = sanPham.getDonGia();
        this.tongTien = sanPham.getDonGia() * soLuong;
        this.ngayDat = LocalDateTime.now();
        this.trangThai = "Đang xử lý";
        this.maDonHang = generateMaDonHang();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
        if (this.donGia != null) {
            this.tongTien = this.donGia * soLuong;
        }
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
        if (this.soLuong != null) {
            this.tongTien = donGia * this.soLuong;
        }
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public LocalDateTime getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(LocalDateTime ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    // Business methods
    /**
     * Tính lại tổng tiền
     */
    public void tinhLaiTongTien() {
        if (donGia != null && soLuong != null) {
            this.tongTien = donGia * soLuong;
        }
    }

    /**
     * Hoàn thành đơn hàng
     */
    public void hoanThanh() {
        this.trangThai = "Đã hoàn thành";
    }

    /**
     * Hủy đơn hàng
     */
    public void huy() {
        this.trangThai = "Đã hủy";
    }

    /**
     * Kiểm tra đơn hàng đã hoàn thành
     */
    public boolean daHoanThanh() {
        return "Đã hoàn thành".equals(trangThai);
    }

    /**
     * Generate mã đơn hàng tự động
     */
    private String generateMaDonHang() {
        return "DH" + System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "DonHang{" +
                "id=" + id +
                ", maDonHang='" + maDonHang + '\'' +
                ", soLuong=" + soLuong +
                ", tongTien=" + tongTien +
                ", trangThai='" + trangThai + '\'' +
                ", ngayDat=" + ngayDat +
                '}';
    }
}
