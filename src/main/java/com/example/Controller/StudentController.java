package com.example.Controller;

import com.example.Service.StudentService;
import com.example.entity.Course;
import com.example.entity.SelectedCourse;
import com.example.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController( StudentService studentService) {
        this.studentService = studentService;
    }

    //Personal Profile and Registration
    @GetMapping("/{studentId}")
    public String showStudentInfo(@PathVariable Integer studentId, Model model) {
        model.addAttribute("student", studentService.getStudentById(studentId));
        return "student-info";
    }
    //register course
    @GetMapping("/{id}/register-course")
    public String showRegisterCourseForm(@PathVariable("id") Integer studentId, Model model) {
        model.addAttribute("studentId", studentId);
        return "register-course";
    }
    @PostMapping("/{id}/register-course")
    public String registerCourse(@PathVariable("id") Integer studentId, @RequestParam("courseId") Integer courseId, Model model) {
        Student student = studentService.getStudentById(studentId);
        Course course = studentService.getCourseById(courseId);
        SelectedCourse selectedCourse = new SelectedCourse();
        selectedCourse.setStudent(student);
        selectedCourse.setCourse(course);
        studentService.saveSelectedCourse(selectedCourse);
        String message = "Course " + course.getCourseName() + " registered successfully";
        model.addAttribute("message", message);
        return "register-course";
    }

    //view all registered courses
    @GetMapping("/{studentId}/all-registered-courses")
    public String showAllCourses(@PathVariable("studentId") Integer studentId, Model model) {
        List<SelectedCourse> selectedCourses = studentService.viewRegisterCourse(studentId);
        model.addAttribute("registeredCourses", selectedCourses);
        return "all-registered-courses";
    }

    // update profile
    @GetMapping("/{studentId}/update-profile")
    public String showUpdateStudentForm(@PathVariable("studentId") Integer studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);
        return "update-profile";
    }
    @PostMapping("/{studentId}/update-profile")
    public String updateStudent(@PathVariable("studentId") Integer studentId, Student updatedStudent, Model model) {
        Student student = studentService.getStudentById(studentId);
        student.setStudentName(updatedStudent.getStudentName());
        student.setEmail(updatedStudent.getEmail());
        student.setPassword(updatedStudent.getPassword());

        studentService.updateStudent(student);
        String message = "Student information updated successfully";
        model.addAttribute("message", message);
        return "update-profile";
    }
    //

    // delete selected course
    @GetMapping("/{studentId}/drop-course")
    public String showDropCourseForm(@PathVariable("studentId") Integer studentId, Model model) {
        List<SelectedCourse> selectedCourses = studentService.viewRegisterCourse(studentId);
        model.addAttribute("selectedCourses", selectedCourses);
        return "drop-course";
    }

    @PostMapping("/{studentId}/drop-course")
    public String dropCourse(@PathVariable("studentId") Integer studentId,
                             @RequestParam("courseId") Integer courseId) {
        studentService.deleteSelectedCourse(studentId, courseId);
        return "drop-course";
    }



}

