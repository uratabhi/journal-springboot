package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
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



}
