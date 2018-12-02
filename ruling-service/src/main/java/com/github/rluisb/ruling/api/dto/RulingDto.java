package com.github.rluisb.ruling.api.dto;

public class RulingDto {

    private String title;
    private String subject;
    private String description;

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
        return "RulingDto{" +
                "title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
