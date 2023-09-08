package com.example.demo.repositories;

import com.example.demo.models.Exam;
import com.example.demo.models.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, String> {
    @Query("select eq from ExamQuestion eq where eq.exam = :exam")
    List<ExamQuestion> findByExamId(@Param("exam") Exam exam);
}
