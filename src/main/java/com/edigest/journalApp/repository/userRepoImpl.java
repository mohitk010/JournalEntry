package com.edigest.journalApp.repository;

import com.edigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//import static sun.jvm.hotspot.gc.shared.CollectedHeapName.Z;

@Component
public class userRepoImpl {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserforSA(){
        Query query = new Query();
        //query.addCriteria(Criteria.where("userName").is("sri"));
//        query.addCriteria(new Criteria().orOperator(
//                Criteria.where("JournalEntries").exists(false),
//                Criteria.where("JournalEntries").size(0)
//        ));
        query.addCriteria(Criteria.where("SentimentAnalysis").is(true));
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"));

        //List<User> users = mongoTemplate.find(query, User.class);
        return mongoTemplate.find(query, User.class);
    }
}
