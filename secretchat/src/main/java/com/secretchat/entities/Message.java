package com.secretchat.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public class Message {

    @Id
    private String id;

    private String content;
    private String sender;
    private LocalDateTime timeStamp;

    public Message( String sender, String content) {
        this.content = content;
        this.sender = sender;
        this.timeStamp = LocalDateTime.now();
    }

    public Message() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
    
}
