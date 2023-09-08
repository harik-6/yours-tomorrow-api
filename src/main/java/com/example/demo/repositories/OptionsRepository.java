package com.example.demo.repositories;

import com.example.demo.models.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionsRepository extends JpaRepository<Option, String> {
}
