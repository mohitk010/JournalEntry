package com.edigest.journalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class journalEntry {
    @Id
    private ObjectId id;
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @NonNull
    private String title;

    private String content;

    private LocalDateTime date;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

//    public String getId(){
//        return id;
//    }
//
//    public void setId(String id){
//        this.id = id;
//    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){

        this.title = title;
    }

    public String getContent(){

        return content;
    }

    public void setContent(String content){
        this.content = content;
    }
}
