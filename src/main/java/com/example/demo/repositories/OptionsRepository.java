package com.example.demo.repositories;

import com.example.demo.models.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OptionsRepository extends JpaRepository<Option, String> {
}
