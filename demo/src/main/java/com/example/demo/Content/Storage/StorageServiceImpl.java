package com.example.demo.Content.Storage;

import com.example.demo.Content.ContentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {
    //private final Path rootLocation;

    private String nfsPath;
    @Autowired
    public StorageServiceImpl() {

    }

    /**
     *
     * store les file dans un répertoire
     * @throws IOException
     */
    @Override
    public void store(Integer userId,
                      MultipartFile file,
                      ContentModel content) throws  IOException {

        Path loc = Paths.get(content.getPath());
        String path = content.getPath()
                + "/" + userId
                + "/" + UUID.randomUUID()
                + "-" + file.getOriginalFilename();
        //Path root = Paths.get(path);
        //System.out.println(path);
        //File storedImage = new File(path);
        //File storedImage = new File("/home/mansour/Téléchargements/moodAgain/demo/src/main/java/com/example/demo/Content/FileUploaded/1");
        //storedImage.createNewFile();
        //file.transferTo(storedImage);
        File storedImage = new File(path);
        if (!storedImage.getParentFile().exists()) {
            storedImage.getParentFile().mkdir();
        }
        storedImage.createNewFile();
        file.transferTo(storedImage);
    }

}
