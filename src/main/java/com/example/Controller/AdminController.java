package com.example.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String showAdminPage() {
        return "admin";
    }

    @GetMapping("/manage-students")
    public String showStudentManagementPage() {
        return "manage-students";
    }

    @GetMapping("/manage-courses")
    public String showCourseManagementPage() {
        return "manage-courses";
    }
}
