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
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @PostMapping("/create/{userName}")
    public ResponseEntity<?> createEntity(@RequestBody JournalEntry myEntry, @PathVariable String userName) {
        try {
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

        @GetMapping("/fetch/{userName}")
        public ResponseEntity<?> getAllEntriesOfUser (@PathVariable String userName) {
            User user = userService.findByUserName(userName);
            List<JournalEntry> all =  user.getJournalEntries();
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


    @DeleteMapping ("/{userName}/{journalId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId journalId, @PathVariable String userName){
        journalEntryService.deleteById(journalId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PutMapping("/{journalId}")
//    public ResponseEntity<?> updateEntry(@PathVariable ObjectId journalId, @RequestBody JournalEntry newEntry){
//        JournalEntry oldEntry = journalEntryService.findEntryById(journalId).orElse(null);
//        if(oldEntry!=null){
//          oldEntry.setTitle(newEntry.getTitle()!= null && newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
//          oldEntry.setContent(newEntry.getContent()!= null && newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
//            journalEntryService.saveEntry(oldEntry);
//            return new ResponseEntity<>(oldEntry, HttpStatus  .OK);
//        }
//
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }



}
