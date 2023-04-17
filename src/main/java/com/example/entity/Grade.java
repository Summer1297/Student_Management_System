package com.example.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Integer gradeId;

    @Column(name = "score")
    private double score;

    @OneToOne
    @JoinColumn(name = "selected_course_id")
    private SelectedCourse selectedCourse;

    public Grade() {
        // Default constructor required by JPA
    }
}
