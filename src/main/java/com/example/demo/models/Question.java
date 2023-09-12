package com.example.demo.models;

import com.example.demo.enums.DifficultyEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "questions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question extends IdAndAuditEntity {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    List<QuestionPhrase> questionPhrases;
    @Enumerated(EnumType.STRING)
    private DifficultyEnum difficulty;
    @NotEmpty
    @JsonProperty("options")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Option> options;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Option> answers;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Subject subject;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Topic topic;
}
