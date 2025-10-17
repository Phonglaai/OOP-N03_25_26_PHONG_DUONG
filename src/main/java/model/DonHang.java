package demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * Model DonHang - Đơn hàng đặt nước mía
 * Đối tượng chính liên kết giữa KhachHang và SanPham
 * Thực hiện chức năng chính của ứng dụng
 */
@Entity
@Table(name = "donhang")
public class DonHang {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Khách hàng không được để trống")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "khachhang_stt", referencedColumnName = "stt")
    private KhachHang khachHang;
    
    @NotNull(message = "Sản phẩm không được để trống")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sanpham_id", referencedColumnName = "id")
    private SanPham sanPham;
    
    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    private Integer soluong;
    
    @NotNull
    private Double tongtien;
    
    @NotNull
    @Column(name = "thoigian")
    private LocalDateTime thoigian;
    
    @Column(length = 20)
    private String trangthai = "Đang xử lý"; // Đang xử lý, Hoàn thành, Đã hủy
    
    @Column(length = 500)
    private String ghichu;
    
    // Constructors
    public DonHang() {
        this.thoigian = LocalDateTime.now();
        this.trangthai = "Đang xử lý";
    }
    
    public DonHang(KhachHang khachHang, SanPham sanPham, Integer soluong) {
        this();
        this.khachHang = khachHang;
        this.sanPham = sanPham;
        this.soluong = soluong;
        tinhTongTien();
    }
    
    // Business method - Tính tổng tiền
    public void tinhTongTien() {
        if (this.sanPham != null && this.soluong != null) {
            this.tongtien = this.sanPham.getDongia() * this.soluong;
        }
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
        tinhTongTien();
    }
    
    public Integer getSoluong() {
        return soluong;
    }
    
    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
        tinhTongTien();
    }
    
    public Double getTongtien() {
        return tongtien;
    }
    
    public void setTongtien(Double tongtien) {
        this.tongtien = tongtien;
    }
    
    public LocalDateTime getThoigian() {
        return thoigian;
    }
    
    public void setThoigian(LocalDateTime thoigian) {
        this.thoigian = thoigian;
    }
    
    public String getTrangthai() {
        return trangthai;
    }
    
    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
    
    public String getGhichu() {
        return ghichu;
    }
    
    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
    
    @Override
    public String toString() {
        return "DonHang{" +
                "id=" + id +
                ", khachHang=" + (khachHang != null ? khachHang.getTen() : "null") +
                ", sanPham=" + (sanPham != null ? sanPham.getTensanpham() : "null") +
                ", soluong=" + soluong +
                ", tongtien=" + tongtien +
                ", thoigian=" + thoigian +
                ", trangthai='" + trangthai + '\'' +
                '}';
    }
}
