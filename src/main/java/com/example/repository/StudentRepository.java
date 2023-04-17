package com.example.repository;

import com.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {


    Student findByEmail(String email);

     // View all students
    List<Student> findAll();

    // Delete student by studentId
    void deleteById(Integer Id);
}


