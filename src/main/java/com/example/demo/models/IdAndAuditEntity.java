package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class IdAndAuditEntity extends IdEntity {
    @Column(updatable = false,nullable = false)
    @CreationTimestamp
    @JsonProperty(value = "created_at",access = JsonProperty.Access.WRITE_ONLY)
    private Date CreatedAt;
    @Column(nullable = false)
    @UpdateTimestamp
    @JsonProperty(value = "updated_at",access = JsonProperty.Access.WRITE_ONLY)
    private Date UpdatedAt;
}
