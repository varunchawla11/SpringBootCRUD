package com.SpringBootCrud.NewCrud.service;

import com.SpringBootCrud.NewCrud.entity.User;

import java.util.List;

public interface UserService {

    User findById(Long Id);

    List<User> findByFirstName(String name);

    List<User> findAllUsers();

    void saveUser(User user);

    void removeUser(Long id);
}
