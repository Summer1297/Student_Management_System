package com.example.repository;

import com.example.entity.Course;
import com.example.entity.SelectedCourse;
import com.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SelectedCourseRepository extends JpaRepository<SelectedCourse, Integer> {
    List<SelectedCourse> findByStudent(Student student);
    void deleteByStudentAndCourse(Student student, Course course);

}
