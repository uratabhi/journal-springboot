package com.abhishekjaiswal.journalApp.controller;

import com.abhishekjaiswal.journalApp.entity.JournalEntry;
import com.abhishekjaiswal.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping("/create")
    public boolean createEntity(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return true;
    }

    @GetMapping("/fetch-all")
    public List<JournalEntry> getAllEntries(){
       return journalEntryService.getAllEntries();
    }

    @GetMapping("/{journalId}")
    public JournalEntry getEntryById(@PathVariable ObjectId journalId){
        return journalEntryService.findEntryById(journalId).orElse(null);
    }

    @DeleteMapping ("/{journalId}")
    public Boolean deleteEntryById(@PathVariable ObjectId journalId){
        journalEntryService.deleteById(journalId);
        return true;
    }

    @PutMapping("/{journalId}")
    public JournalEntry updateEntry(@PathVariable ObjectId journalId, @RequestBody JournalEntry newEntry){
        JournalEntry oldEntry = journalEntryService.findEntryById(journalId).orElse(null);
        if(oldEntry!=null){
          oldEntry.setTitle(newEntry.getTitle()!= null && newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
          oldEntry.setContent(newEntry.getContent()!= null && newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
        }
        journalEntryService.saveEntry(oldEntry);
        return newEntry;
    }



}
