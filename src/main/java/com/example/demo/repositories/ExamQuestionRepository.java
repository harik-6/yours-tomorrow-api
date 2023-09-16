package com.example.demo.repositories;

import com.example.demo.models.Exam;
import com.example.demo.models.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, String> {
    @Query("select eq from ExamQuestion eq where eq.exam = :exam")
    List<ExamQuestion> findByExamId(@Param("exam") Exam exam);

    @Query(value = "select * from exam_questions where exam_id = ?1 and question_id = ?2",nativeQuery = true)
    Optional<ExamQuestion> findByExamIdAndQuestionId(String examId, String questionId);

    @Modifying
    @Query(value = "delete from exam_questions where exam_id = ?1",nativeQuery = true)
    void removeAllQuestionIdForAnExamId(String examId);
}
