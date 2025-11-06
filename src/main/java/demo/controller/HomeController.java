package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Chao mung den voi App Order Nuoc Mia!");
        return "index";
    }

    @GetMapping("/khachhang")
    public String khachHang(Model model) {
        model.addAttribute("title", "Quan ly Khach Hang");
        return "khachhang";
    }

    @GetMapping("/sanpham")
    public String sanPham(Model model) {
        model.addAttribute("title", "Quan ly San Pham");
        return "sanpham";
    }

    @GetMapping("/doanhthu")
    public String doanhThu(Model model) {
        model.addAttribute("title", "Quan ly Doanh Thu");
        return "doanhthu";
    }
}