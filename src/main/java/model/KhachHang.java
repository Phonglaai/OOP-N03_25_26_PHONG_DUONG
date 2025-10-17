package demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Model KhachHang - Khách hàng đặt nước mía
 */
@Entity
@Table(name = "khachhang")
public class KhachHang {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stt;
    
    @NotBlank(message = "Tên khách hàng không được để trống")
    @Size(min = 2, max = 100, message = "Tên phải từ 2-100 ký tự")
    @Column(nullable = false, length = 100)
    private String ten;
    
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại phải có 10 số và bắt đầu bằng 0")
    @Column(nullable = false, unique = true, length = 10)
    private String sodienthoai;
    
    @Column(length = 200)
    private String diachi;
    
    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL)
    private List<DonHang> lichSuMuaHang = new ArrayList<>();
    
    // Constructors
    public KhachHang() {}
    
    public KhachHang(String ten, String sodienthoai) {
        this.ten = ten;
        this.sodienthoai = sodienthoai;
    }
    
    // Business Methods
    public void taoKhachHang(String ten, String sodienthoai, String diachi) {
        this.ten = ten;
        this.sodienthoai = sodienthoai;
        this.diachi = diachi;
    }
    
    public List<DonHang> hienThiTT() {
        return this.lichSuMuaHang;
    }
    
    public void capNhatTT(String ten, String sodienthoai, String diachi) {
        if (ten != null && !ten.trim().isEmpty()) {
            this.ten = ten;
        }
        if (sodienthoai != null && !sodienthoai.trim().isEmpty()) {
            this.sodienthoai = sodienthoai;
        }
        if (diachi != null) {
            this.diachi = diachi;
        }
    }
    
    // Getters & Setters
    public Long getStt() { 
        return stt; 
    }
    
    public void setStt(Long stt) { 
        this.stt = stt; 
    }
    
    public String getTen() { 
        return ten; 
    }
    
    public void setTen(String ten) { 
        this.ten = ten; 
    }
    
    public String getSodienthoai() { 
        return sodienthoai; 
    }
    
    public void setSodienthoai(String sodienthoai) { 
        this.sodienthoai = sodienthoai; 
    }
    
    public String getDiachi() { 
        return diachi; 
    }
    
    public void setDiachi(String diachi) { 
        this.diachi = diachi; 
    }
    
    public List<DonHang> getLichSuMuaHang() { 
        return lichSuMuaHang; 
    }
    
    public void setLichSuMuaHang(List<DonHang> lichSuMuaHang) { 
        this.lichSuMuaHang = lichSuMuaHang; 
    }
    
    @Override
    public String toString() {
        return "KhachHang{" +
                "stt=" + stt +
                ", ten='" + ten + '\'' +
                ", sodienthoai='" + sodienthoai + '\'' +
                ", diachi='" + diachi + '\'' +
                '}';
    }
}
