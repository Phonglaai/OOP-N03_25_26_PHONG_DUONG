package demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Model DoanhThu - Báo cáo doanh thu theo ngày
 */
@Entity
@Table(name = "doanhthu")
public class DoanhThu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Thời gian không được để trống")
    @Column(nullable = false, unique = true)
    private LocalDate thoigian;
    
    @NotNull(message = "Tổng tiền không được để trống")
    @DecimalMin(value = "0.0", message = "Tổng tiền phải >= 0")
    @Column(nullable = false)
    private Double tongtien;
    
    @NotNull(message = "Số đơn không được để trống")
    @Min(value = 0, message = "Số đơn phải >= 0")
    @Column(nullable = false)
    private Integer sodon;
    
    @Column(length = 1000)
    private String chitiet;
    
    // Constructors
    public DoanhThu() {
        this.thoigian = LocalDate.now();
        this.tongtien = 0.0;
        this.sodon = 0;
    }
    
    public DoanhThu(LocalDate thoigian) {
        this.thoigian = thoigian;
        this.tongtien = 0.0;
        this.sodon = 0;
    }
    
    // Business Methods
    public void taoDoanhThu(LocalDate thoigian) {
        this.thoigian = thoigian;
        this.tongtien = 0.0;
        this.sodon = 0;
    }
    
    public String xemChiTiet() {
        return String.format("Ngày: %s - Tổng tiền: %.0f VNĐ - Số đơn: %d", 
                            thoigian, tongtien, sodon);
    }
    
    public void capNhatDoanhThu(Double tongtien, Integer sodon, String chitiet) {
        if (tongtien != null && tongtien >= 0) {
            this.tongtien = tongtien;
        }
        if (sodon != null && sodon >= 0) {
            this.sodon = sodon;
        }
        if (chitiet != null) {
            this.chitiet = chitiet;
        }
    }
    
    public void tinhDoanhThuTuDonHang(List<DonHang> danhSachDonHang) {
        this.sodon = danhSachDonHang.size();
        this.tongtien = danhSachDonHang.stream()
                                        .mapToDouble(DonHang::getTongtien)
                                        .sum();
    }
    
    public void themDonHang(DonHang donHang) {
        if (donHang != null && donHang.getTongtien() != null) {
            this.tongtien += donHang.getTongtien();
            this.sodon++;
        }
    }
    
    // Getters & Setters
    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }
    
    public LocalDate getThoigian() { 
        return thoigian; 
    }
    
    public void setThoigian(LocalDate thoigian) { 
        this.thoigian = thoigian; 
    }
    
    public Double getTongtien() { 
        return tongtien; 
    }
    
    public void setTongtien(Double tongtien) { 
        this.tongtien = tongtien; 
    }
    
    public Integer getSodon() { 
        return sodon; 
    }
    
    public void setSodon(Integer sodon) { 
        this.sodon = sodon; 
    }
    
    public String getChitiet() { 
        return chitiet; 
    }
    
    public void setChitiet(String chitiet) { 
        this.chitiet = chitiet; 
    }
    
    @Override
    public String toString() {
        return "DoanhThu{" +
                "id=" + id +
                ", thoigian=" + thoigian +
                ", tongtien=" + tongtien +
                ", sodon=" + sodon +
                '}';
    }
}
