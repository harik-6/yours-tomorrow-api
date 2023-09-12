package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subjects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject extends IdEntity {
    private String name;
    @JsonIgnore
    @Override
    public String getId() {
        return super.getId();
    }
}
