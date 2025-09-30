package com.abhishekjaiswal.journalApp.controller;

import com.abhishekjaiswal.journalApp.entity.JournalEntry;
import com.abhishekjaiswal.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping("/create")
    public ResponseEntity<JournalEntry> createEntity(@RequestBody JournalEntry myEntry) {
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

        @GetMapping("/fetch-all")
        public ResponseEntity<?> getAllEntries () {
            List<JournalEntry> all =  journalEntryService.getAllEntries();
            if(all !=null && !all.isEmpty()){
                return new ResponseEntity<>(all, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @GetMapping("/{journalId}")
        public ResponseEntity<JournalEntry> getEntryById (@PathVariable ObjectId journalId){
            Optional<JournalEntry> journalEntry = journalEntryService.findEntryById(journalId);
            return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }


    @DeleteMapping ("/{journalId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId journalId){
        journalEntryService.deleteById(journalId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{journalId}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId journalId, @RequestBody JournalEntry newEntry){
        JournalEntry oldEntry = journalEntryService.findEntryById(journalId).orElse(null);
        if(oldEntry!=null){
          oldEntry.setTitle(newEntry.getTitle()!= null && newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
          oldEntry.setContent(newEntry.getContent()!= null && newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
