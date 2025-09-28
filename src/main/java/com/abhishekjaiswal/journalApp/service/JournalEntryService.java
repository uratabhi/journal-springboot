package com.abhishekjaiswal.journalApp.service;

import com.abhishekjaiswal.journalApp.entity.JournalEntry;
import com.abhishekjaiswal.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllEntries(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findEntryById(ObjectId journalId){
        return journalEntryRepository.findById(journalId);
    }

    public void deleteById(ObjectId journalId){
        journalEntryRepository.deleteById(journalId);
    }




}

// controller ---->> service ------>> repository
