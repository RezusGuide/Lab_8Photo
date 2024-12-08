package test.lab_8photo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@Controller
public class PhotoController {
    private final PhotoRepository photoRepository;
    private final PhotoService photoService;

    public PhotoController(PhotoRepository photoRepository, PhotoService photoService) {
        this.photoRepository = photoRepository;
        this.photoService = photoService;
    }

    @GetMapping("/upload")
    public String uploadPage(Model model) {
        model.addAttribute("photos", photoRepository.findAll());
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadPhoto(@RequestParam("file") MultipartFile file, Model model) {
        try {
            photoService.uploadPhoto(file);
            model.addAttribute("successMessage", "File uploaded successfully!");
        } catch (IOException e) {
            model.addAttribute("errorMessage", "Error uploading file!");
        }
        return "upload";
    }
}
