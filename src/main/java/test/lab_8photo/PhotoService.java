package test.lab_8photo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/images/";


    public Photo uploadPhoto(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.write(filePath, file.getBytes());

        Photo photo = new Photo();
        photo.setFileName(fileName);

        String imageLink = "/images/" + fileName;
        photo.setImageLink(imageLink);

        return photoRepository.save(photo);
    }

}
