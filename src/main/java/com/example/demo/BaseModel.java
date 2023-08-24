package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@MappedSuperclass
@Getter
@Setter
public class BaseModel {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    @CreationTimestamp
    @JsonProperty("created_at")
    private Date CreatedAt;
    @UpdateTimestamp
    @JsonProperty("updated_at")
    private Date UpdatedAt;
}
