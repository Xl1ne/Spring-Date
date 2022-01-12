package com.simple.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "api/v1/student")
    public List<Student> getStudent(){
       return studentService.getStudent();
    }

    @PostMapping(path = "api/v1/student")
    public void addStudent(Student student){
        studentService.addStudent(student);
    }

    @DeleteMapping(path = "api/v1/student/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id){
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "api/v1/student/{studentId}")
    public  void updateStudent(@PathVariable("studentId") Long id, @RequestParam(required = false) String firstname, @RequestParam(required = false) String lastname){
        studentService.updateStudent(id, firstname, lastname);
    }
}
