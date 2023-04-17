package com.example.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "selected_course")
public class SelectedCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selected_Course_id")
    private Integer selectedCourseId;

    @ManyToOne
    @JoinColumn(name = "student_user_id", referencedColumnName = "student_user_id")
    private Student student;


    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;

    @OneToOne(mappedBy = "selectedCourse", cascade = CascadeType.ALL)
    private Grade grade;

    // Constructors, getters and setters

    public void setSelectedCourseId(Integer selectedCourseId) {
        this.selectedCourseId = selectedCourseId;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public Integer getSelectedCourseId() {
        return selectedCourseId;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }


}

