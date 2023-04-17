package com.example.Controller;


import com.example.Service.AdminService;
import com.example.entity.Course;
import com.example.repository.CourseRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin/manage-courses")
public class AdminCourseController {

    private final CourseRepository courseRepository;
    private final AdminService adminService;

    public AdminCourseController(CourseRepository courseRepository, AdminService adminService) {
        this.courseRepository = courseRepository;
        this.adminService = adminService;
    }

    // Show add course form
    @GetMapping("/add-course")
    public String showAddCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "add-course";
    }

    // Add course
    @PostMapping("/add-course")
    public String addCourse(@ModelAttribute("course") Course course, @RequestParam(value = "_stay", defaultValue = "false") boolean stay) {
        adminService.addCourse(course);
        if (stay) {
            return "redirect:/admin/manage-courses/add-course";
        }
        return "redirect:/admin/manage-courses";
    }

    // Show delete course form
    @GetMapping("/delete-course")
    public String showDeleteCourseForm() {
        return "delete-course";
    }

    // Delete course
    @PostMapping("/delete-course")
    public String deleteCourse(@RequestParam("id") Integer id) {
        adminService.deleteCourse(id);
        return "redirect:/admin/manage-courses/delete-course?success";
    }


    // Show search course form
    @GetMapping("/search-course")
    public String showSearchCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "search-course";
    }
    // Search course
    @PostMapping("/search-course")
    public String searchCourse(@RequestParam("id") Integer courseId, Model model) {
        Course courses = adminService.getCourseById(courseId);
        model.addAttribute("courses", courses);
        return "search-course-result";
    }

    // course catalog
    @GetMapping("all-courses")
    public String showAllCourses(Model model) {
        List<Course> courses = adminService.getAllCourses();
        model.addAttribute("courses", courses);
        return "all-courses";
    }

}




