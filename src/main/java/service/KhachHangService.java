package demo.service;
import demo.model.KhachHang;
import demo.repository.KhachHangRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class KhachHangService {
    private final KhachHangRepository repo;

    public KhachHangService(KhachHangRepository repo) {
        this.repo = repo;
    }

    public List<KhachHang> getAll() {
        return repo.findAll();
    }

    public Optional<KhachHang> findById(Long id) {
        return repo.findById(id);
    }

    public KhachHang save(KhachHang kh) {
        return repo.save(kh);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
