package com.example.demo.Content.Storage;

import com.example.demo.Content.ContentModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {

    void store(Integer userId, MultipartFile file, ContentModel content) throws  IOException;
}
