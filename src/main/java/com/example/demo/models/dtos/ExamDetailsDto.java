package com.example.demo.models.dtos;

import com.example.demo.models.Exam;
import com.example.demo.models.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamDetailsDto {
    private Exam exam;
    private List<Question> questions;
}
