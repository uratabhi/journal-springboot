package com.abhishekjaiswal.journalApp.service;

import com.abhishekjaiswal.journalApp.entity.JournalEntry;
import com.abhishekjaiswal.journalApp.entity.User;
import com.abhishekjaiswal.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private  UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userService.findByUserName(userName);
        if(user == null){
            throw new RuntimeException("User not found: " + userName);
        }
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.createUser(user);
    }

    public List<JournalEntry> getAllEntries(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findEntryById(ObjectId journalId){
        return journalEntryRepository.findById(journalId);
    }

    public void deleteById(ObjectId journalId, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(journalId));
        userService.createUser(user);
        journalEntryRepository.deleteById(journalId);
    }

}
