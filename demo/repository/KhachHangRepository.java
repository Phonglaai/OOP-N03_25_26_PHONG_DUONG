package demo.repository;
import org.springframework.data.jpa.repositorypo.JpaRepository;
import com.example.demo.model.KhachHang;

public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {}
