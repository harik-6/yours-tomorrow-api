package com.example.demo.exam;

import com.example.demo.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name="exams")
@Getter
@Setter
public class Exam extends BaseModel {
    private String name;
    @NotEmpty
    @JsonProperty("date_time")
    @Column(nullable = false)
    private Date dateTime;
    @JsonProperty("duration_in_minutes")
    @NotEmpty
    @Column(nullable = false)
    private Integer durationInMinutes;
    @JsonProperty("total_easy_questions")
    private Integer totalEasyQuestions;
    @JsonProperty("total_medium_questions")
    private Integer totalMediumQuestions;
    @JsonProperty("total_hard_questions")
    private Integer totalHardQuestions;
}
