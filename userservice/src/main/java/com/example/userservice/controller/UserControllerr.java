package com.example.userservice.controller;

import com.example.userservice.domain.User;
import com.example.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserControllerr{

    private final UserService userService;

    public UserControllerr(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id){
        return userService.getUserById(id);
    }

    @PostMapping()
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public String updateUserById(@PathVariable UUID id, @RequestBody User user){
        return userService.updateUserById(user,id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable UUID id){
        return deleteUser(id);
    }
}
