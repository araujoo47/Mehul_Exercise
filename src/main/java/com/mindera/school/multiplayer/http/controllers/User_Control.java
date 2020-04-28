package com.mindera.school.multiplayer.http.controllers;

import com.mindera.school.multiplayer.http.models.User;
import com.mindera.school.multiplayer.services.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class User_Control {

    private User_Service user_Service;

    @Autowired
    public User_Control(User_Service user_Service) {
        this.user_Service = user_Service;
    }

    @PostMapping("")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        var newUser = user_Service.createUser(user);
        return ResponseEntity.created(getUserURI(newUser.getId())).build();
    }

    @GetMapping("/{userID}")
    public User getUser(@PathVariable("userID") String userID) {
        return user_Service.getUser(userID);
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return user_Service.getAllUsers();
    }

    @DeleteMapping("/{userID}")
    public void deleteUser(@PathVariable("userID") String userID) {
        user_Service.deleteUser(userID);
    }

    @PutMapping("/{userID}")
    public User replaceUser(
            @PathVariable("userID") String userID,
            @RequestBody User user
    )
    {
        return user_Service.replaceUser(userID, user);
    }

    private URI getUserURI(String id) {
        return URI.create(String.format("/users/%s", id));
    }
}
