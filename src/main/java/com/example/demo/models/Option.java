package com.example.demo.models;

import com.example.demo.enums.LanguageEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "options")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Option extends IdEntity {
    private String value;
    @Enumerated(EnumType.STRING)
    private LanguageEnum language;
}
