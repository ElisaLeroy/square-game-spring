package com.example.demo.controller;

import com.example.demo.repository.jpa.connection.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void saveUser(@RequestBody UserEntity userData) {
        userService.saveUser(userData);
    }

    @GetMapping("/{userId}")
    public Object getUserById(@PathVariable int userId){
        return userService.getUserById(userId);
    }

    @GetMapping
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/{userId}")
    public void updateUser(@RequestBody UserEntity userData, @PathVariable int userId) {
        userService.update(userData, userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }


}
