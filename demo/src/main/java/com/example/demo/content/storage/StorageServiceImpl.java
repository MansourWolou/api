package com.example.demo.content.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.demo.content.ContentModel;
import com.example.demo.content.ContentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
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


    @Value("${application.bucket.name}")
    private String bucketName;

    //private String objectKey;

    @Autowired
    private AmazonS3 s3Client;

    public String uploadFile(MultipartFile file,String fileName) {
        File fileObj = convertMultiPartFileToFile(file);
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        return "File uploaded : " + fileName;
    }


    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            System.out.println("Error converting multipartFile to file");
            System.out.print(e);
            //log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
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
