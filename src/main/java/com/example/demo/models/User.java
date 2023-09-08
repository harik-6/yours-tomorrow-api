package com.example.demo.models;

import com.example.demo.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends IdAndAuditEntity {
    @NotEmpty
    @Column(nullable = false)
    private String name;
    @NotEmpty
    @Column(nullable = false,unique = true)
    private String email;
    @NotEmpty
    @Column(nullable = false,unique = true)
    private String phone;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender = GenderEnum.NA;

}
