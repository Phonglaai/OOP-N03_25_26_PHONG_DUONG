package demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity KhachHang - Quản lý thông tin khách hàng
 */
@Entity
@Table(name = "khach_hang")
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String ten;

    @Column(name = "so_dien_thoai", nullable = false, unique = true, length = 15)
    private String soDienThoai;

    @Column(length = 255)
    private String diaChi;

    @Column(length = 100)
    private String email;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DonHang> danhSachDonHang = new ArrayList<>();

    // Constructors
    public KhachHang() {
        this.ngayTao = LocalDateTime.now();
    }

    public KhachHang(String ten, String soDienThoai) {
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.ngayTao = LocalDateTime.now();
    }

    public KhachHang(String ten, String soDienThoai, String diaChi, String email) {
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.email = email;
        this.ngayTao = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public List<DonHang> getDanhSachDonHang() {
        return danhSachDonHang;
    }

    public void setDanhSachDonHang(List<DonHang> danhSachDonHang) {
        this.danhSachDonHang = danhSachDonHang;
    }

    // Business methods
    /**
     * Hiển thị thông tin khách hàng
     */
    public String hienThiThongTin() {
        return String.format("Khách hàng: %s | SĐT: %s | Email: %s", 
                            ten, soDienThoai, email != null ? email : "N/A");
    }

    /**
     * Tính tổng số đơn hàng
     */
    public int tongSoDonHang() {
        return danhSachDonHang != null ? danhSachDonHang.size() : 0;
    }

    /**
     * Tính tổng tiền đã chi tiêu
     */
    public double tongTienChiTieu() {
        if (danhSachDonHang == null || danhSachDonHang.isEmpty()) {
            return 0.0;
        }
        return danhSachDonHang.stream()
                .mapToDouble(DonHang::getTongTien)
                .sum();
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "id=" + id +
                ", ten='" + ten + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", email='" + email + '\'' +
                ", ngayTao=" + ngayTao +
                '}';
    }
}
