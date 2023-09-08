package com.example.demo.models;

import com.example.demo.enums.GenderEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseModel {
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
