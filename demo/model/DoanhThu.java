package demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class DoanhThu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate thoigian;
    private double tongtien;
    private int sodon;

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getThoigian() { return thoigian; }
    public void setThoigian(LocalDate thoigian) { this.thoigian = thoigian; }

    public double getTongtien() { return tongtien; }
    public void setTongtien(double tongtien) { this.tongtien = tongtien; }

    public int getSodon() { return sodon; }
    public void setSodon(int sodon) { this.sodon = sodon; }
}
