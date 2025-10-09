package com.example.springbootproject.controller;

import com.example.springbootproject.model.SinhVien;
import com.example.springbootproject.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sinhvien")
public class SinhVienController {

    @Autowired
    private SinhVienService sinhVienService;

    @GetMapping
    public List<SinhVien> getAllSinhVien() {
        return sinhVienService.getAllSinhVien();
    }

    @PostMapping
    public SinhVien createSinhVien(@RequestBody SinhVien sinhVien) {
        return sinhVienService.createSinhVien(sinhVien);
    }

    @GetMapping("/monhoc/{monHocId}")
    public List<SinhVien> getSinhVienByMonHoc(@PathVariable Long monHocId) {
        return sinhVienService.getSinhVienByMonHoc(monHocId);
    }
}
