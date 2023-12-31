package com.example.demo.services;

import com.example.demo.models.Exam;
import com.example.demo.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Exam getExamById(String examId) {
        return examRepository.findById(examId).orElse(null);
    }

    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    public Exam updateAnExam(Exam exam) {
        return examRepository.save(exam);
    }

}
