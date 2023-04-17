package com.example.Controller;

import com.example.Service.AdminService;
import com.example.entity.Course;
import com.example.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin/manage-students")
public class AdminStudentController {
    private final AdminService adminService;
    //private final StudentRepository studentRepository;

    public AdminStudentController(AdminService adminService) {
        this.adminService = adminService;
        //this.studentRepository = studentRepository;
    }
//add Student
    @GetMapping("/add-student")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }
    @PostMapping("/add-student")
    public String addStudent(@ModelAttribute("student") Student student, @RequestParam(value = "_stay", defaultValue = "false") boolean stay) {
        adminService.addStudent(student);
        if (stay) {
            return "redirect:/admin/manage-students/add-student";
        }
        return "redirect:/admin/manage-students";
    }

    // Show delete student form
    @GetMapping("/delete-student")
    public String showDeleteStudentForm() {
        return "delete-student";
    }

    // Delete student
    @PostMapping("/delete-student")
    public String deleteStudent(@RequestParam("id") Integer id) {
        adminService.deleteStudent(id);
        return "redirect:/admin/manage-students/delete-student?success";
    }

    // all students
    @GetMapping("/all-students")
    public String showAllStudents(Model model) {
        List<Student> students = adminService.getAllStudents();
        model.addAttribute("students", students);
        return "all-students";
    }


    // Show search course form
    @GetMapping("/search-student")
    public String showSearchStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "search-student";
    }
    // Search course
    @PostMapping("/search-student")
    public String searchStudent(@RequestParam("id") Integer studentId, Model model) {
        Student students = adminService.getStudentById(studentId);
        model.addAttribute("student", students);
        return "search-student-result";
    }













    @GetMapping("/update-student")
    public String showUpdateStudentForm(@RequestParam("id") Integer studentId, Model model) {
        Student student = adminService.getStudentById(studentId);
        model.addAttribute("student", student);
        return "update-student";
    }

    @PostMapping("/update-student")
    public String updateStudent(@RequestParam("id") Integer studentId, @ModelAttribute("student") Student student, @RequestParam(value = "_stay", defaultValue = "false") boolean stay) {
        student.setStudentId(studentId);
        adminService.saveOrUpdateStudent(student);
        if (stay) {
            return "redirect:/admin/manage-students/update-student?id=" + student.getStudentId();
        }
        return "redirect:/admin/manage-students";
    }




}
