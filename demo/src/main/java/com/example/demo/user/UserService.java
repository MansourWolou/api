package com.example.demo.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {

    List<UserModel> findAll();
    Optional<UserModel> findById(Integer id);
    UserModel save(UserModel user);

    void deleteById(Integer id);

    void delete(UserModel user);
}
