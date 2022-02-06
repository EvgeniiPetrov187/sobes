package com.petrov.dao;

import com.petrov.persist.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {

    List<Student> findAll();

    Optional<Student> findById(Long id);

    void saveOrUpdate(Student student);

    void delete(Student student);
}
