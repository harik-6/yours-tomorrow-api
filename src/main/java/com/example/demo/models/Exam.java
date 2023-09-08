package com.example.demo.models;

import com.example.demo.enums.ExamTypeEnum;
import com.example.demo.enums.GroupEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "exams")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exam extends IdAndAuditEntity {
    @NotEmpty
    private String name;
    private String description;
    private Date startTime;
    private Date endTime;
    @JsonProperty("duration_in_minutes")
    private int durationInMinutes;
    @JsonProperty("total_questions")
    private int totalQuestions;
    @Column(name = "exam_group")
    @Enumerated(EnumType.STRING)
    private GroupEnum group;
    @Column(name = "exam_type")
    @Enumerated(EnumType.STRING)
    private ExamTypeEnum type;
}
