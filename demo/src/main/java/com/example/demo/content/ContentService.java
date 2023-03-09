package com.example.demo.content;

import java.util.List;
import java.util.Optional;

public interface ContentService {
    List<ContentModel> findAll();
    Optional<ContentModel> findById(Integer id);

    Optional<ContentModel> findByDescription(String description);
    ContentModel save(ContentModel content);

    void deleteById(Integer id);
    void delete(ContentModel content);
}
