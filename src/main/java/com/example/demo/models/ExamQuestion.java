package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "exam_questions")
@Getter
@Setter
public class ExamQuestion extends IdEntity  {
    @ManyToOne
    private Exam exam;
    @ManyToOne
    private Question question;
}
