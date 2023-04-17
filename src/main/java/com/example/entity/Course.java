package com.example.entity;
import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer courseId;



    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_credits")
    private int courseCredits;

    @OneToMany(mappedBy = "course")
    private List<SelectedCourse> selectedCourses;

    public Course() {
        // Default constructor required by JPA
    }


    public Integer getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseCredits() {
        return courseCredits;
    }

    public void setCourseCredits(int courseCredits) {
        this.courseCredits = courseCredits;
    }
}