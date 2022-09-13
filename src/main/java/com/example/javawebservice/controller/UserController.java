package com.example.javawebservice.controller;

import com.example.javawebservice.dto.User;
import com.example.javawebservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();

    }

    @GetMapping("{id}")
    public User getUser(@PathVariable int id){
        return userService.getUser(id);
    }
    @PostMapping
    public User addUser(@RequestBody User user){
        return  userService.addUser(user);
    }

}
