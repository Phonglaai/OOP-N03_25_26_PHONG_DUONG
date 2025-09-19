import java.util.*;

public class DoanhThu {
    private String thoiGian;
    private double tongTien;
    private int soDon;

    public DoanhThu(String thoiGian) {
        this.thoiGian = thoiGian;
        this.tongTien = 0;
        this.soDon = 0;
    }

    public static DoanhThu taoDoanhThu(String thoiGian) {
        return new DoanhThu(thoiGian);
    }

    public void capNhatDoanhThu(double tienMoi, int donMoi) {
        this.tongTien = tienMoi;
        this.soDon = donMoi;
    }

    public static void xoaDoanhThu(List<DoanhThu> danhSach, String thoiGian) {
        danhSach.removeIf(dt -> dt.thoiGian.equals(thoiGian));
    }

    public void hienThiBaoCao() {
        System.out.println("Thời gian: " + thoiGian + ", Tổng tiền: " + tongTien + ", Số đơn: " + soDon);
    }
}

