package com.example.springbootproject.repository;

import com.example.springbootproject.model.SinhVien;
import com.example.springbootproject.model.MonHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien, Long> {
    List<SinhVien> findByMonHoc(MonHoc monHoc);
}
