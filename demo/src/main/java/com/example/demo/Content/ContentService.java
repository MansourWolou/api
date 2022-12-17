package com.example.demo.Content;

import com.example.demo.user.UserModel;

import java.util.List;
import java.util.Optional;

public interface ContentService {
    List<ContentModel> findAll();
    Optional<ContentModel> findById(Integer id);
    ContentModel save(ContentModel content);

    void deleteById(Integer id);

    void delete(ContentModel content);
}
