package com.example.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "admin")

@NoArgsConstructor
@AttributeOverride(name = "userId", column = @Column(name = "admin_user_id"))
@AttributeOverride(name = "password", column = @Column(name = "admin_password"))



public class Admin extends User {

    @Column(name = "admin_email")
    private String email;

    public Admin(Integer userId, String password,  String email) {
        super(userId, password, UserType.STUDENT);
        this.email = email;
    }


    public String getAdminPassword() {
        return getPassword();
    }
}