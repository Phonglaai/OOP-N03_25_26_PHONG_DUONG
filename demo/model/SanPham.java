package demo.model;

import jakarta.persistence.*;

@Entity
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tensanpham;
    private int soluong;
    private double dongia;

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTensanpham() { return tensanpham; }
    public void setTensanpham(String tensanpham) { this.tensanpham = tensanpham; }

    public int getSoluong() { return soluong; }
    public void setSoluong(int soluong) { this.soluong = soluong; }

    public double getDongia() { return dongia; }
    public void setDongia(double dongia) { this.dongia = dongia; }
}
