package com.petrov.dao;

import com.petrov.persist.Student;

import java.util.List;

public interface StudentDao {

    List<Student> findAll();

    Student findById(Long id);

    void saveOrUpdate(Student student);

    void delete(Student student);
}
