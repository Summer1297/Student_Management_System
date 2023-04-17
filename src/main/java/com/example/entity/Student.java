package com.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "userId", column = @Column(name = "student_user_id")),
        @AttributeOverride(name = "password", column = @Column(name = "student_password"))
})
public class Student extends User {

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_email")
    private String email;

    @OneToMany(mappedBy = "student")
    private List<SelectedCourse> selectedCourses;



    public Student(Integer userId, String password, String studentName, String email) {
        super(userId, password, UserType.STUDENT);
        this.studentName = studentName;
        this.email = email;
    }


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStudentId(){
        return getUserId();
    }

    public String getStudentPassword() {
        return getPassword();
    }
    public void setStudentId(Integer studentId) {
        super.setUserId(studentId);
    }



}
