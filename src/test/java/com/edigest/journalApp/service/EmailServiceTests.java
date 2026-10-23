package com.edigest.journalApp.service;

import com.edigest.journalApp.servies.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;

    @Test
    public void testSend(){
        emailService.sendMail("@gmail.com", "first mail", "JSK");
    }
}
