package com.example.demo.Content.Storage;

import com.example.demo.Content.ContentModel;
import com.example.demo.Content.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {
    private final ContentService contentService;

    @Autowired
    public StorageServiceImpl(ContentService contentService) {

        this.contentService = contentService;
    }

    /**
     *  store les file dans un répertoire
     * @param userId
     * @param file
     * @param nfsPath
     * @throws IOException
     * TODO: prendre en compte la mise en place des exceptions
     *
     */
    @Override
    public void store(Integer userId,
                      MultipartFile file,
                      String nfsPath) throws  IOException {


        File storedImage = new File(nfsPath);
        if (!storedImage.getParentFile().exists()) {
            storedImage.getParentFile().mkdir();
        }
        storedImage.createNewFile();
        file.transferTo(storedImage);
    }

    public byte[] downloadContent(String description) throws IOException {
        Optional<ContentModel> fileData = contentService.findByDescription(description);
        String filePath=fileData.get().getPath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;

    }

}
