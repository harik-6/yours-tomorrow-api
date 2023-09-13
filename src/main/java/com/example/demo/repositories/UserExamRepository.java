package com.example.demo.repositories;

import com.example.demo.models.UserExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserExamRepository extends JpaRepository<UserExam, String> {
    @Query(value = "select * from user_exams where user_id = ?1 and exam_id = ?2 limit 1",nativeQuery = true)
    Optional<UserExam> findByUserIdAndExamId(String userId, String examId);
}
