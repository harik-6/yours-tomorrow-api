package com.example.demo.question;

import com.example.demo.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "questions")
@Getter
@Setter
public class Question extends BaseModel {
    private String question;
    @JsonProperty("correct_answer")
    private String correctAnswer;
    @ElementCollection(targetClass = String.class)
    private List<String> answers;
    private String subject;
    private String difficulty;

    public String getCorrectAnswer() {
        return null;
    }
}
