package com.edigest.journalApp.service;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserSrrviceTests {
    @Autowired
    private UserRepo userRepo;

    @Test
    public void testFindByUserName(){
        User user = userRepo.findByUserName("sri");
        //assertNotNull(userRepo.findByUserName("kalpana"));
        //assertEquals(4, 2+2);
        assertTrue(!user.getJournalEntries().isEmpty());
    }
}
