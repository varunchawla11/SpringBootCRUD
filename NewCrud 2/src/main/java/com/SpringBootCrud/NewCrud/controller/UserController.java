package com.SpringBootCrud.NewCrud.controller;

import com.SpringBootCrud.NewCrud.entity.User;
import com.SpringBootCrud.NewCrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/view")
    public ResponseEntity<?> viewAllUsers(){
        List<User> users = null;
        try {
            users = userService.findAllUsers();
            if(users == null)
                return new ResponseEntity<>("No records found", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/view/{id}")
    public ResponseEntity<?> viewUser(@PathVariable(value = "id") Long id){
        User user = null;
        try {
            user = userService.findById(id);
            if(user == null)
                return new ResponseEntity<>("No user found for this Id", HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(user, HttpStatus.OK);

    }


    @PostMapping(value = "/add")
    public ResponseEntity<?> addNewUser(
            @RequestBody User user) {
        try{
            if(user.getAge() == null || user.getAge().isEmpty() || user.getFirstName() == null ||
                    user.getFirstName().isEmpty() || user.getLastName() == null || user.getLastName().isEmpty() ||
                    user.getDepartment() == null || user.getDepartment().isEmpty()) {
                return new ResponseEntity<>("Required fields are fistName, lastName, age and department | not null", HttpStatus.OK);
            }
            userService.saveUser(user);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id){
        User user = null;
        try {
            user = userService.findById(id);
            if(user != null)
                userService.removeUser(id);
            else
                return new ResponseEntity<>("No user found for this Id", HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PutMapping(value = "/alter/{id}")
    public ResponseEntity<String> alterUser(@PathVariable(value = "id") Long id,
                                            @RequestBody User user){

        User modifiedUser = userService.findById(id);
        if(user == null)
            return new ResponseEntity<>("No record exists for this Id", HttpStatus.BAD_REQUEST);
        if(user.getAge() == null || user.getAge().isEmpty() || user.getFirstName() == null ||
                user.getFirstName().isEmpty() || user.getLastName() == null || user.getLastName().isEmpty() ||
                user.getDepartment() == null || user.getDepartment().isEmpty()) {
            return new ResponseEntity<>("No field should be empty", HttpStatus.BAD_REQUEST);
        }
        modifiedUser.setAge(user.getAge());

        modifiedUser.setFirstName(user.getFirstName());

        modifiedUser.setLastName(user.getLastName());

        modifiedUser.setDepartment(user.getDepartment());

        userService.saveUser(modifiedUser);

        return new ResponseEntity<>("Changes saved successfully", HttpStatus.OK);
    }
}
