package com.petrov.controller;

import com.petrov.persist.Student;
import com.petrov.persist.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testFindAll () throws Exception {
        Student student1 = new Student(null, "test_name_1", 20);
        Student student2 = new Student(null, "test_name_2", 30);

        student1 = studentRepository.save(student1);
        student2 = studentRepository.save(student2);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(student1.getName())))
                .andExpect(jsonPath("$[1].name", is(student2.getName())));
    }

    @Test
    public void testEditStudent() throws Exception {
        Student student = new Student(null, "test_name_1", 20);
        student = studentRepository.save(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + student.getId()+"/id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(student.getName())))
                .andExpect(jsonPath("$.age", is(student.getAge())));
    }
}
