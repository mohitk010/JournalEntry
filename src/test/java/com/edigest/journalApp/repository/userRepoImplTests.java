package com.edigest.journalApp.repository;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.servies.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;
@SpringBootTest
public class userRepoImplTests {
    @Autowired
    private userRepoImpl userRepo;

    @Test
    public void saveNewUser(){
        List<User> users = userRepo.getUserforSA();
        assertNotNull(users);

        List<String> names = users.stream()
                .map(User::getUserName)
                .toList();

        System.out.println(names);
    }
}
