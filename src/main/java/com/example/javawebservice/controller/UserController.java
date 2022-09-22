package com.example.javawebservice.controller;

import com.example.javawebservice.dto.Post;
import com.example.javawebservice.dto.ResponseUser;
import com.example.javawebservice.enteties.AppUser;
import com.example.javawebservice.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users/")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("{id}")
    @PreAuthorize("@authService.isCorrectUserLogin(#id)")
    public ResponseUser getUser(@PathVariable int id){
        return userService.getUser(id);
    }
    @PostMapping
    @PreAuthorize("@authService.isAdmin()")
    public ResponseUser addAppUser(@RequestBody AppUser appUser){
        return userService.addAppUser(appUser);
    }
    @PutMapping("{id}")
    @PreAuthorize("@authService.isCorrectUserLogin(#id)")
    public ResponseUser updateAppUser(@RequestBody AppUser appUser, @PathVariable int id){
        return userService.updateAppUser(appUser, id);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("@authService.isCorrectUserLogin(#id)")
    public void deleteAppuser(@PathVariable int id){
        userService.terminateAppUser(id);
    }
    @GetMapping("{id}/posts")
    public List<Post> getAllPostsByUser(@PathVariable int id){
        return userService.getAllPostsByUser(id);
    }
}
