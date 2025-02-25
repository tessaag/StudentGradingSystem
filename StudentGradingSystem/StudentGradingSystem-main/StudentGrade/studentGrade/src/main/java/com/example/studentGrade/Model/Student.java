package com.example.studentGrade.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Map;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("rollNumber")
    private String rollNumber;

    @JsonProperty("name")
    private String name;

    @JsonProperty("department")
    private String department;

    @JsonProperty("marks")
    @ElementCollection
    private Map<String, String> marks;

    @JsonProperty("grades")
    @ElementCollection
    private Map<String, String> grades;

    @JsonProperty("finalStatus")
    private String finalStatus;

    public Student() {
    }

    public Student(Long id, String rollNumber, String name, String department, Map<String, String> marks, Map<String, String> grades, String finalStatus) {
        this.id = id;
        this.rollNumber = rollNumber;
        this.name = name;
        this.department = department;
        this.marks = marks;
        this.grades = grades;
        this.finalStatus = finalStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Map<String, String> getMarks() {
        return marks;
    }

    public void setMarks(Map<String, String> marks) {
        this.marks = marks;
    }

    public Map<String, String> getGrades() {
        return grades;
    }

    public void setGrades(Map<String, String> grades) {
        this.grades = grades;
    }

    public String getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(String finalStatus) {
        this.finalStatus = finalStatus;
    }
}
