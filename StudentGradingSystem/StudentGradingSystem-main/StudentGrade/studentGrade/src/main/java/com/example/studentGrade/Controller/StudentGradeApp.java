package com.example.studentGrade.Controller;

import com.example.studentGrade.Model.Student;
import com.example.studentGrade.Repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentGradeApp {
    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    @PostMapping("/studentEntry")
    public ResponseEntity<Map<String, String>> studentEntry(@RequestBody Student student) {
        studentRepository.save(student);

        Map<String, String> response = new HashMap<>();
        response.put("Status", "Student details recorded successfully!");
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping("/findResult")
    public ResponseEntity<Map<String, Object>> findResult(@RequestBody Map<String, Object> data) {
        String rollNumber = (String) data.get("RollNumber");
        Map<String, String> marks = (Map<String, String>) data.get("Marks");

        int totalMarks = 0;
        int maxMarks = 0;
        Map<String, String> grades = new HashMap<>();

        for (String subject : marks.keySet()) {
            String[] score = marks.get(subject).split("/");
            int obtainedMarks = Integer.parseInt(score[0]);
            int subjectMaxMarks = Integer.parseInt(score[1]);

            totalMarks += obtainedMarks;
            maxMarks += subjectMaxMarks;

            String grade = calculateGrade((double) obtainedMarks / subjectMaxMarks * 100);
            grades.put(subject, grade);
        }

        double percentage = ((double) totalMarks / maxMarks) * 100;
        String finalGrade = calculateGrade(percentage);
        String qualificationStatus = finalGrade.equals("F") ? "Failed" : "Passed";

        Student student = studentRepository.findByRollNumber(rollNumber);
        if (student != null) {
            student.setGrades(grades);
            studentRepository.save(student);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("Final Grade", finalGrade);
        response.put("Qualification Status", qualificationStatus);
        response.put("Grades", grades);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/getStudents")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    private String calculateGrade(double percentage) {
        if (percentage >= 90) return "S";
        else if (percentage >= 85) return "A+";
        else if (percentage >= 80) return "A";
        else if (percentage >= 75) return "B+";
        else if (percentage >= 70) return "B";
        else if (percentage >= 65) return "C+";
        else if (percentage >= 60) return "C";
        else if (percentage >= 55) return "D+";
        else if (percentage >= 50) return "D";
        else return "F";
    }
}
