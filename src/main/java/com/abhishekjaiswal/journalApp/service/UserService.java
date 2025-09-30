package com.abhishekjaiswal.journalApp.service;

import com.abhishekjaiswal.journalApp.entity.User;
import com.abhishekjaiswal.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user){
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findUserById(ObjectId userId){
        return userRepository.findById(userId);
    }

    public void deleteUserById(ObjectId userId) {
        userRepository.deleteById(userId);
    }

    public User findByUserName (String userName){
        return userRepository.findByUserName(userName);
    }




}

// controller ---->> service ------>> repository
