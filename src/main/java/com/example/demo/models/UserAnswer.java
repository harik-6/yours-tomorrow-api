package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user_answers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswer extends IdAndAuditEntity {
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserExam userExam;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ExamQuestion examQuestion;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Option answer;
}
