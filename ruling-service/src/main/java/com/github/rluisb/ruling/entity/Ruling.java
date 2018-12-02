package com.github.rluisb.ruling.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rulings")
public class Ruling {

    @Id
    private String id;
    private String title;
    private String subject;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Ruling{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", subject=" + subject +
                ", description='" + description + '\'' +
                '}';
    }
}

