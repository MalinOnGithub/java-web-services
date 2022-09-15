package com.example.javawebservice.controller;

import com.example.javawebservice.dto.Post;
import com.example.javawebservice.dto.ResponseUser;
import com.example.javawebservice.enteties.AppUser;
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
    @GetMapping("{id}")
    public ResponseUser getUser(@PathVariable int id){
        return userService.getUser(id);
    }

    @PostMapping
    public ResponseUser addAppUser(@RequestBody AppUser appUser){
        return userService.addAppUser(appUser);
    }

    @PutMapping("{id}")
    public ResponseUser updateAppUser(@RequestBody AppUser appUser, @PathVariable int id){
        return userService.updateAppUser(appUser, id);
    }

    @DeleteMapping("{id}")
    public void deleteAppuser(@PathVariable int id){
        userService.terminateAppUser(id);
    }

    @GetMapping("{id}/posts")
    public List<Post> getAllPostsByUser(@PathVariable int id){
        return userService.getAllPostsByUser(id);
    }
/*    @PostMapping
    public AppUser addUser(@RequestBody ResponseUser user){
        return  userService.addUser(user);
    }
    @PutMapping("{id}")
    public AppUser uppdateUser(@RequestBody ResponseUser user, @PathVariable int id){
        return userService.updateExistingUser(user, id);
    }

    @DeleteMapping("{id}")
    public AppUser deleteUser(@PathVariable int id){
        return userService.terminateUser(id);
    }*/


}
