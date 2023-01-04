package com.example.demo.Content.Storage;

import com.example.demo.Content.ContentModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface StorageService {


    String uploadFile(MultipartFile file,String fileName);

    byte[] downloadFile(String fileName);

    String deleteFile(String fileName);


    void store(Integer userId,
               MultipartFile file,
               String nfsPath) throws  IOException;

    public byte[] downloadContent(String description) throws IOException;
}
