package demo.model;

import jakarta.persistence.*;

@Entity
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stt;

    private String ten;
    private String sodienthoai;

    // Getter & Setter
    public Long getStt() { return stt; }
    public void setStt(Long stt) { this.stt = stt; }

    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }

    public String getSodienthoai() { return sodienthoai; }
    public void setSodienthoai(String sodienthoai) { this.sodienthoai = sodienthoai; }
}
