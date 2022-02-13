package com.petrov.controller;

import com.petrov.persist.Student;
import com.petrov.persist.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/all")
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}/id")
    public Student findById(@PathVariable("id") long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with id = " + id + " not exists"));
    }

    @PutMapping
    public Student update(@RequestBody Student student) {
        if (student.getId() == null) {
            throw new IllegalArgumentException("Can't update student with id null");
        }
        return studentRepository.save(student);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student save(@RequestBody Student student) {
        if (student.getId() != null) {
            throw new IllegalArgumentException("Can't create user with id not null");
        }
        return studentRepository.save(student);
    }

    @DeleteMapping("/{id}/id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        studentRepository.deleteById(id);
    }
}
