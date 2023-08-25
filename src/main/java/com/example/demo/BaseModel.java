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
    @Column(updatable = false,nullable = false)
    @CreationTimestamp
    @JsonProperty(value = "created_at",access = JsonProperty.Access.WRITE_ONLY)
    private Date CreatedAt;
    @Column(nullable = false)
    @UpdateTimestamp
    @JsonProperty(value = "updated_at",access = JsonProperty.Access.WRITE_ONLY)
    private Date UpdatedAt;
}
