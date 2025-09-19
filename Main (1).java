import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<DoanhThu> dsDT = new ArrayList<>();
        DoanhThu dt1 = DoanhThu.taoDoanhThu("19/09/2025");
        dsDT.add(dt1);
        dt1.capNhatDoanhThu(24000, 3);
        dt1.hienThiBaoCao();
        System.out.println("Số lượng trước xóa: " + dsDT.size());
        DoanhThu.xoaDoanhThu(dsDT, "19/09/2025");
        System.out.println("Số lượng sau xóa: " + dsDT.size());
    }
}
