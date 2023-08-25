package com.example.demo.question;

import com.example.demo.BaseModel;
import com.example.demo.enums.DifficultyEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "questions")
@Setter
@Getter
public class Question extends BaseModel {
    @Size(min = 10, max = 1000,message = "Invalid question")
    private String question;
    @NotEmpty
    @JsonProperty(value = "correct_answer",access = JsonProperty.Access.WRITE_ONLY)
    private String correctAnswer;
    @ElementCollection(targetClass = String.class)
    private List<String> answers;
    @NotEmpty
    private String subject;
    @Enumerated(EnumType.STRING)
    private DifficultyEnum difficulty;
}
