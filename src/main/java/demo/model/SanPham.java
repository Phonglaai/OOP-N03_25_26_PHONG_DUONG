package demo.model;

import jakarta.persistence.*;

/**
 * Entity SanPham - Quản lý thông tin sản phẩm
 */
@Entity
@Table(name = "san_pham")
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String maSanPham;

    @Column(name = "ten_san_pham", nullable = false, length = 200)
    private String tenSanPham;

    @Column(name = "don_gia", nullable = false)
    private Double donGia;

    @Column(name = "so_luong_ton_kho")
    private Integer soLuongTonKho;

    @Column(length = 500)
    private String moTa;

    @Column(name = "loai_san_pham", length = 50)
    private String loaiSanPham;

    @Column(name = "trang_thai")
    private Boolean trangThai; // true = còn bán, false = ngừng bán

    // Constructors
    public SanPham() {
        this.trangThai = true;
        this.soLuongTonKho = 0;
    }

    public SanPham(String maSanPham, String tenSanPham, Double donGia) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
        this.trangThai = true;
        this.soLuongTonKho = 0;
    }

    public SanPham(String maSanPham, String tenSanPham, Double donGia, Integer soLuongTonKho, String loaiSanPham) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
        this.soLuongTonKho = soLuongTonKho;
        this.loaiSanPham = loaiSanPham;
        this.trangThai = true;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public Integer getSoLuongTonKho() {
        return soLuongTonKho;
    }

    public void setSoLuongTonKho(Integer soLuongTonKho) {
        this.soLuongTonKho = soLuongTonKho;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    // Business methods
    /**
     * Kiểm tra sản phẩm còn hàng
     */
    public boolean conHang() {
        return soLuongTonKho != null && soLuongTonKho > 0 && trangThai;
    }

    /**
     * Kiểm tra có đủ số lượng
     */
    public boolean duSoLuong(int soLuongYeuCau) {
        return soLuongTonKho != null && soLuongTonKho >= soLuongYeuCau;
    }

    /**
     * Giảm tồn kho
     */
    public void giamTonKho(int soLuong) throws Exception {
        if (!duSoLuong(soLuong)) {
            throw new Exception("Không đủ số lượng tồn kho. Còn lại: " + soLuongTonKho);
        }
        this.soLuongTonKho -= soLuong;
    }

    /**
     * Tăng tồn kho
     */
    public void tangTonKho(int soLuong) {
        this.soLuongTonKho = (this.soLuongTonKho != null ? this.soLuongTonKho : 0) + soLuong;
    }

    /**
     * Tính thành tiền cho số lượng
     */
    public double tinhThanhTien(int soLuong) {
        return donGia * soLuong;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "id=" + id +
                ", maSanPham='" + maSanPham + '\'' +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", donGia=" + donGia +
                ", soLuongTonKho=" + soLuongTonKho +
                ", trangThai=" + (trangThai ? "Còn bán" : "Ngừng bán") +
                '}';
    }
}
