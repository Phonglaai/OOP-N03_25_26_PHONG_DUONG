package demo.service;
import com.example.demo.model.KhachHang;
import com.example.demo.repository.KhachHangRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KhachHangService {
    private final KhachHangRepository repo;

    public KhachHangService(KhachHangRepository repo) {
        this.repo = repo;
    }

    public List<KhachHang> getAll() {
        return repo.findAll();
    }

    public KhachHang save(KhachHang kh) {
        return repo.save(kh);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
