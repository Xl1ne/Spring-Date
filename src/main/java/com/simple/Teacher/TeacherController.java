package com.simple.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController{

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(path = "api/v1/teacher")
    public List<Teacher> getTeacher(){
        return teacherService.getTeacher();
    }

    @PostMapping(path = "api/v1/teacher")
    public void registerNewTeacher(Teacher teacher){
        teacherService.addNewTeacher(teacher);
    }

    @DeleteMapping(path = "api/v1/teacher/{teacherId}")
    public void deleteTeacher(@PathVariable("teacherId") Long id){
        teacherService.deleteTeacher(id);
    }

    @PutMapping(path = "api/v1/teacher/{teacherId}")
    public void updateTeacher(@PathVariable("teacherId") Long id, @RequestParam(required = false) String firstname, @RequestParam(required = false) String lastname){
        teacherService.updateTeacher(id, firstname, lastname);
    }
}
