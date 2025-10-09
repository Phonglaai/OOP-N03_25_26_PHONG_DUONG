@Controller
public class HomeController {

    @Autowired
    private SinhVienService sinhVienService;

    @GetMapping("/")
    public String index(Model model) {
        List<SinhVien> sinhViens = sinhVienService.getAllSinhVien();
        model.addAttribute("sinhViens", sinhViens);
        return "index";
    }
}
