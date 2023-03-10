package com.example.demo.Content;

import com.example.demo.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContentRepository extends JpaRepository<ContentModel, Integer> {
    Optional<ContentModel> findByDescription(String description);
}
