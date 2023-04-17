package com.example.Service;

import com.example.entity.Course;
import com.example.entity.SelectedCourse;
import com.example.entity.Student;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.CourseRepository;
import com.example.repository.SelectedCourseRepository;
import com.example.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final SelectedCourseRepository selectedCourseRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, SelectedCourseRepository selectedCourseRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.selectedCourseRepository = selectedCourseRepository;
        this.courseRepository = courseRepository;
    }

    // Student login
    public Student login(String email, String password) {
        Student student = studentRepository.findByEmail(email);
        if (student == null || student.getStudentPassword() == null || !student.getStudentPassword().equals(password)) {
            return null;
        } else {
            return student;
        }
    }

    // register course
    public SelectedCourse saveSelectedCourse(SelectedCourse selectedCourse) {
        return selectedCourseRepository.save(selectedCourse);
    }


    //all-registered-courses
    public List<SelectedCourse> viewRegisterCourse(Integer studentId){
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            return selectedCourseRepository.findByStudent(student);
        } else {
            return null;
        }
    }


    // drop course
    public void deleteSelectedCourse(Integer studentId, Integer courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);
        if (student != null && course != null) {
            selectedCourseRepository.deleteByStudentAndCourse(student, course);
        }
    }



    //update student profile
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }




    public Course getCourseById(Integer courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
    }


    public Student getStudentById(Integer studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }




}