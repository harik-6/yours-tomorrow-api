package com.example.demo.repositories;

import com.example.demo.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubjectRepository extends JpaRepository<Subject, String> {
    @Query("SELECT s FROM Subject s WHERE s.name = :name")
    Subject findByName(@Param("name") String name);
}
