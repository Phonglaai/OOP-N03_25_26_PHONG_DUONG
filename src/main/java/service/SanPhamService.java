package demo.service;

import demo.model.SanPham;
import demo.repository.SanPhamRespository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SanPhamService {
    private final SanPhamRespository repo;

    public SanPhamService(SanPhamRespository repo) {
        this.repo = repo;
    }

    public List<SanPham> getAll() {
        return repo.findAll();
    }

    public Optional<SanPham> findById(Long id) {
        return repo.findById(id);
    }

    public SanPham save(SanPham sp) {
        return repo.save(sp);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
