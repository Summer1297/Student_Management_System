package com.example.repository;

import com.example.entity.Admin;
import com.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByUserIdAndPassword(String userId, String password);

    Admin findByEmail(String email);
}

