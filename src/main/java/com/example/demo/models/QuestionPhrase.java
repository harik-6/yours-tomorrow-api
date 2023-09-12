package com.example.demo.models;

import com.example.demo.enums.LanguageEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionPhrase extends IdEntity {
    @Size(min = 10,max = 500, message = "question phrase size shold be between 10 and 500")
    @Column(length = 500)
    private String value;
    @Enumerated(EnumType.STRING)
    private LanguageEnum language;
}
