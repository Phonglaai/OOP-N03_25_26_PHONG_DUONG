package demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Model SanPham - Menu nước mía
 */
@Entity
@Table(name = "sanpham")
public class SanPham {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 2, max = 100, message = "Tên sản phẩm phải từ 2-100 ký tự")
    @Column(nullable = false, length = 100)
    private String tensanpham;
    
    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng phải >= 0")
    @Column(nullable = false)
    private Integer soluong;
    
    @NotNull(message = "Đơn giá không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Đơn giá phải lớn hơn 0")
    @Column(nullable = false)
    private Double dongia;
    
    @Column(length = 500)
    private String mota;
    
    @Column(length = 200)
    private String hinhanh;
    
    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL)
    private List<DonHang> danhSachDonHang = new ArrayList<>();
    
    // Constructors
    public SanPham() {}
    
    public SanPham(String tensanpham, Integer soluong, Double dongia) {
        this.tensanpham = tensanpham;
        this.soluong = soluong;
        this.dongia = dongia;
    }
    
    // Business Methods
    public void themSP(String tensanpham, Integer soluong, Double dongia, String mota) {
        this.tensanpham = tensanpham;
        this.soluong = soluong;
        this.dongia = dongia;
        this.mota = mota;
    }
    
    public String hienThiSP() {
        return String.format("SP: %s - Giá: %.0f VNĐ - Còn: %d", tensanpham, dongia, soluong);
    }
    
    public void capNhatSP(String tensanpham, Integer soluong, Double dongia, String mota) {
        if (tensanpham != null && !tensanpham.trim().isEmpty()) {
            this.tensanpham = tensanpham;
        }
        if (soluong != null && soluong >= 0) {
            this.soluong = soluong;
        }
        if (dongia != null && dongia > 0) {
            this.dongia = dongia;
        }
        if (mota != null) {
            this.mota = mota;
        }
    }
    
    // Getters & Setters
    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }
    
    public String getTensanpham() { 
        return tensanpham; 
    }
    
    public void setTensanpham(String tensanpham) { 
        this.tensanpham = tensanpham; 
    }
    
    public Integer getSoluong() { 
        return soluong; 
    }
    
    public void setSoluong(Integer soluong) { 
        this.soluong = soluong; 
    }
    
    public Double getDongia() { 
        return dongia; 
    }
    
    public void setDongia(Double dongia) { 
        this.dongia = dongia; 
    }
    
    public String getMota() { 
        return mota; 
    }
    
    public void setMota(String mota) { 
        this.mota = mota; 
    }
    
    public String getHinhanh() { 
        return hinhanh; 
    }
    
    public void setHinhanh(String hinhanh) { 
        this.hinhanh = hinhanh; 
    }
    
    public List<DonHang> getDanhSachDonHang() { 
        return danhSachDonHang; 
    }
    
    public void setDanhSachDonHang(List<DonHang> danhSachDonHang) { 
        this.danhSachDonHang = danhSachDonHang; 
    }
    
    @Override
    public String toString() {
        return "SanPham{" +
                "id=" + id +
                ", tensanpham='" + tensanpham + '\'' +
                ", soluong=" + soluong +
                ", dongia=" + dongia +
                ", mota='" + mota + '\'' +
                '}';
    }
}
