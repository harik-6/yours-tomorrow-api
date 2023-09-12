package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "topics")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic extends IdEntity{
    private String name;
    @JsonIgnore
    @Override
    public String getId() {
        return super.getId();
    }
}
