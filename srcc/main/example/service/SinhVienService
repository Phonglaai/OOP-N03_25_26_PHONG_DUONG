package com.example.springbootproject.service;

import com.example.springbootproject.model.SinhVien;
import com.example.springbootproject.model.MonHoc;
import com.example.springbootproject.repository.SinhVienRepository;
import com.example.springbootproject.repository.MonHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinhVienService {

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    private MonHocRepository monHocRepository;

    public List<SinhVien> getAllSinhVien() {
        return sinhVienRepository.findAll();
    }

    public SinhVien getSinhVienById(Long id) {
        return sinhVienRepository.findById(id).orElse(null);
    }

    public SinhVien createSinhVien(SinhVien sinhVien) {
        return sinhVienRepository.save(sinhVien);
    }

    public List<SinhVien> getSinhVienByMonHoc(Long monHocId) {
        MonHoc monHoc = monHocRepository.findById(monHocId).orElse(null);
        return sinhVienRepository.findByMonHoc(monHoc);
    }
}
