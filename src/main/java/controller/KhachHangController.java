package demo.controller;

import demo.model.KhachHang;
import demo.service.KhachHangService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/khachhang")
public class KhachHangController {
    private final KhachHangService service;

    public KhachHangController(KhachHangService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("listKH", service.getAll());
        return "khachhang";
    }

    @PostMapping("/save")
    public String save(KhachHang kh) {
        service.save(kh);
        return "redirect:/khachhang";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/khachhang";
    }
}
