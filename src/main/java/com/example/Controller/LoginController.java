package com.example.Controller;

import com.example.Service.AdminService;
import com.example.Service.StudentService;
import com.example.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final StudentService studentService;
    private final AdminService adminService;
    private User loginUser;

    public LoginController(StudentService studentService, AdminService adminService) {
        this.studentService = studentService;
        this.adminService = adminService;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        //User loginUser;
        //System.out.println(loginForm.getUserType());     [bug: userType == null]
        if (loginForm.getUserType() == UserType.STUDENT) {
            loginUser = studentService.login(loginForm.getEmail(), loginForm.getPassword());
            if (loginUser != null) {
                System.out.println("Student login success for email: " + loginForm.getEmail());
                return "redirect:/students/"+ loginUser.getUserId();
            }
        } else if (loginForm.getUserType() == UserType.ADMIN) {
            loginUser = adminService.login(loginForm.getEmail(), loginForm.getPassword());
            if (loginUser != null) {
                //System.out.println("Admin login success for email: " + loginForm.getEmail());
                return "redirect:/admin";
            }
        }
        //System.out.println("Login failed for email: " + loginForm.getEmail()+" == "+loginForm.getPassword());
        model.addAttribute("error", "Email or password error");
        return "login";
    }

    public User getLoginUser() {
        return loginUser;
    }

}
