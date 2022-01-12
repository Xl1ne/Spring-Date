package com.simple.Student;

import com.simple.Teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class StudentService {

    private final StudentRepo studentRepo;


    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getStudent(){
        return studentRepo.findAll();
    }

    public void addStudent(Student student) {
    studentRepo.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepo.findById(id);
        boolean exists = studentRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException("student with id " + id + " does not exists");
        }
        studentRepo.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String firstname, String lastname) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("student with id " + id + " does not exists"));
        if (firstname != null && firstname.length() > 0 && !Objects.equals(student.getFirstname(), firstname)){
            student.setFirstname(firstname);
        }

        if (lastname != null && lastname.length() > 0 && !Objects.equals(student.getLastname(), lastname)){
            student.setLastname(lastname);
        }
    }
}