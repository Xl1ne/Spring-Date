package com.simple.Teacher;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class TeacherService{

    private final TeacherRepo teacherRepo;

    @Autowired
    public TeacherService(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    public List<Teacher> getTeacher(){
        return teacherRepo.findAll();
    }

    public void addNewTeacher(Teacher teacher) {
    teacherRepo.save(teacher);
    }

    public void deleteTeacher(Long id) {
    teacherRepo.findById(id);
        boolean exists = teacherRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException("teacher with id " + id + " does not exists");
        }
        teacherRepo.deleteById(id);
    }

    @Transactional
    public void updateTeacher(Long id, String firstname, String lastname) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("student with id " + id + " does not exists"));
        if (firstname != null && firstname.length() > 0 && !Objects.equals(teacher.getFirstname(), firstname)){
            teacher.setFirstname(firstname);
        }

        if (lastname != null && lastname.length() > 0 && !Objects.equals(teacher.getLastname(), lastname)){
            teacher.setLastname(lastname);
        }
    }
}
