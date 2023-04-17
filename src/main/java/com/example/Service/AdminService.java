package com.example.Service;

import com.example.entity.*;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.AdminRepository;
import com.example.repository.CourseRepository;
import com.example.repository.StudentRepository;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class AdminService {

    private final StudentRepository studentRepository;
    private final AdminRepository adminRepository;
    private final CourseRepository courseRepository;

    public AdminService(StudentRepository studentRepository, AdminRepository adminRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.adminRepository = adminRepository;
        this.courseRepository = courseRepository;
    }

    //Admin login
    public Admin login(String email, String password) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin == null || admin.getAdminPassword() == null || !admin.getPassword().equals(password)) {
            return null;
        } else {return admin;}
    }

    // add student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }


    //show all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    //delete student
    public void deleteStudent(Integer studentId) {
        Student student = getStudentById(studentId);
        studentRepository.delete(student);
    }

    //add course
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }


    //update
    public void saveOrUpdateStudent(Student student) {
        Student existingStudent = studentRepository.findByEmail(student.getEmail());
        if (existingStudent != null) {
            existingStudent.setStudentName(student.getStudentName());
            studentRepository.save(existingStudent);
        } else {
            studentRepository.save(student);
        }
    }



    public Student getStudentById(Integer studentId) {
        if (studentId == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
    }

    // show courses list
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Integer courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
    }
    // delete course
    public void deleteCourse(Integer courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
        courseRepository.delete(course);
    }
}