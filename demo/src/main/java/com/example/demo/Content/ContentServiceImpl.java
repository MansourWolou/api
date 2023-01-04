package com.example.demo.Content;

import com.example.demo.user.UserModel;
import com.example.demo.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentServiceImpl implements ContentService{

    private final ContentRepository contentRepository;

    public ContentServiceImpl(ContentRepository repository) {
        this.contentRepository = repository;
    }

    @Override
    public List<ContentModel> findAll() {
        return contentRepository.findAll();
    }

    @Override
    public Optional<ContentModel> findById(Integer id) {
        return contentRepository.findById(id);
    }

    @Override
    public Optional<ContentModel> findByDescription(String description){
        return contentRepository.findByDescription(description);
    }

    @Override
    public ContentModel save(ContentModel content) {
        return contentRepository.save(content);
    }

    @Override
    public void deleteById(Integer id) {
        contentRepository.deleteById(id);
    }

    @Override
    public void delete(ContentModel content) {
        contentRepository.delete(content);
    }

}
