package com.SpringBootCrud.NewCrud.service;

import com.SpringBootCrud.NewCrud.entity.User;
import com.SpringBootCrud.NewCrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Override
    public User findById(Long Id){
        return userRepository.findOneById(Id);
    }


    @Override
    public List<User> findByFirstName(String firstName) {

        List<User> al = new ArrayList<>();
        al = userRepository.findByFirstName(firstName);
        return al;
    }


    @Override
    public List<User> findAllUsers(){
        List users = userRepository.findAll();
        return users;
    }

    @Override
    public void saveUser(User user){
        userRepository.save(user);
    }


    @Override
   public void removeUser(Long id){
        userRepository.deleteById(id);
    }
}
