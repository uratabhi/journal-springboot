package com.abhishekjaiswal.journalApp.repository;

import com.abhishekjaiswal.journalApp.entity.JournalEntry;

import com.abhishekjaiswal.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName (String userName );

}
