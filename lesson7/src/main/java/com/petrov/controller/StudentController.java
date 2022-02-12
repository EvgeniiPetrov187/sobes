package com.petrov.controller;

import com.petrov.persist.Student;
import com.petrov.persist.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String find(Model model, @RequestParam("nameFilter") Optional<String> name) {
        List<Student> students;
        if (name.isPresent()) {
            students = studentRepository.findByNameStartsWith(name.get());
        } else {
            students = studentRepository.findAll();
        }
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/new")
    public String setNewStudent(Model model) {
        model.addAttribute("student", new Student());
        return "new_student";
    }

    @GetMapping("/{id}")
    public String editStudent(@PathVariable("id") Long id, Model model) {
        model.addAttribute("student", studentRepository.findById(id).orElse(null));
        return "new_student";
    }

    @PostMapping
    public String save(Student student) {
        studentRepository.save(student);
        return "redirect:/student";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", studentRepository.findById(id));
        studentRepository.deleteById(id);
        return "redirect:/student";
    }
}
