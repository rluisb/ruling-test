package com.github.rluisb.ruling.entity.builder;

import com.github.rluisb.ruling.entity.Ruling;

public class RulingBuilder {

    private Ruling ruling;

    public RulingBuilder() {
        this.ruling = new Ruling();
    }

    public static RulingBuilder builder() {
        return new RulingBuilder();
    }

    public RulingBuilder withTitle(String title) {
        this.ruling.setTitle(title);
        return this;
    }

    public RulingBuilder withDescription(String description) {
        this.ruling.setDescription(description);
        return this;
    }

    public RulingBuilder withSubject(String subject) {
        this.ruling.setSubject(subject);
        return this;
    }

    public RulingBuilder withId(String id) {
        this.ruling.setId(id);
        return this;
    }

    public Ruling build(){
        return this.ruling;
    }
}
