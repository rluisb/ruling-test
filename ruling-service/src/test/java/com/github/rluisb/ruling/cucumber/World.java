package com.github.rluisb.ruling.cucumber;

import com.github.rluisb.ruling.api.dto.RulingDto;
import com.github.rluisb.ruling.entity.Ruling;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("cucumber-glue")
public class World {
    public Ruling ruling;
    public List<Ruling> rulings;
    public Integer status;
    public String errorMessage;
    public RulingDto rulingDto;
    public String id;
}

