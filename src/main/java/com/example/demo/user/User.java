package com.example.demo.user;

import com.example.demo.BaseModel;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseModel {
    private String name;
    private String email;
    private String phone;
    private Integer age;
    private String gender;

}
