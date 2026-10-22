package com.edigest.journalApp.servies;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.entity.journalEntry;
import com.edigest.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    @Transactional
    public void saveEntry(journalEntry jEntry, String userName){
        try {
            User user = userService.findByUserName(userName);
            jEntry.setDate(LocalDateTime.now());
            journalEntry entry = journalEntryRepo.save(jEntry);
            user.getJournalEntries().add(entry);
            userService.saveUser(user);
        } catch (Exception e) {
            logger.info("first official lof of this project");
            throw new RuntimeException("an error has occured while saving the entry", e);
        }
    }
    public void saveEntry(journalEntry jEntry){
        journalEntryRepo.save(jEntry);
    }
    public List<journalEntry> getAll(){
        return journalEntryRepo.findAll();
    }
    public Optional<journalEntry> findByid(ObjectId id){
        return journalEntryRepo.findById(id);
    }
    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean done = false;
        try {
        User user = userService.findByUserName(userName);
        done = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        if (done) {
            userService.saveUser(user);
            journalEntryRepo.deleteById(id);
        }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return done;
    }


}

//controller ---> service ---> repo