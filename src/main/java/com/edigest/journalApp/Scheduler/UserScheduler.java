package com.edigest.journalApp.Scheduler;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.entity.journalEntry;
import com.edigest.journalApp.repository.userRepoImpl;
import com.edigest.journalApp.servies.EmailService;
import com.edigest.journalApp.servies.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {
    @Autowired
    private EmailService emailService;

    @Autowired
    private userRepoImpl userRepo;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Scheduled(cron = "0 * * ? * *")
    public void fetchUsersAndSendMail(){
        List<User> users = userRepo.getUserforSA();
        for(User user: users){
            List<journalEntry> JournalEntries = user.getJournalEntries();
            List<String> filteredEntries = JournalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getContent()).collect(Collectors.toList());
            String join = String.join(" ",filteredEntries);
            String sentiment = sentimentAnalysisService.getSentiment(join);
            emailService.sendMail(user.getEmail(), "sentiment for last 7 days", sentiment);
        }

    }
}
