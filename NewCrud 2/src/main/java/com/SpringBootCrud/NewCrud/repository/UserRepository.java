package com.SpringBootCrud.NewCrud.repository;


import com.SpringBootCrud.NewCrud.entity.User;

import java.util.List;

public interface UserRepository extends BaseRepository<User, Long> {
    User findOneById(Long Id);

    List<User> findByFirstName(String fistName);

}
