package com.example.demo.question;

import com.example.demo.BaseModel;
import com.example.demo.enums.DifficultyEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "questions")
@Setter
@Getter
@NoArgsConstructor
public class Question extends BaseModel {
    @Size(min = 10, max = 1000,message = "Invalid question")
    private String question;
    @NotEmpty
    private String subject;
    @NotEmpty
    @Enumerated(EnumType.STRING)
    private DifficultyEnum difficulty;
    @NotEmpty
    @JsonProperty("options")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Option> options;
    @NotEmpty
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Option answer;
}
