package com.abhishekjaiswal.journalApp.controller;

import com.abhishekjaiswal.journalApp.entity.JournalEntry;
import com.abhishekjaiswal.journalApp.entity.User;
import com.abhishekjaiswal.journalApp.service.JournalEntryService;
import com.abhishekjaiswal.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    private List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping ("/create")
    public void createUser(@RequestBody User user){
        userService.createUser(user);
    }


}
