package com.example.demo.Content;

import com.example.demo.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<ContentModel, Integer> {
}
