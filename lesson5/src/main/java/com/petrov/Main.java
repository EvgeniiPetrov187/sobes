package com.petrov;

import com.petrov.dao.StudentDaoImpl;
import com.petrov.persist.Student;

public class Main {

    private static int randomMark = (int) (Math.random() * 5);

    public static void main(String[] args) {
        Student student = new Student(6L, "Mike 3", 1);
        StudentDaoImpl studentDao = new StudentDaoImpl();

        for (int i = 0; i < 1000; i++) {
            Student st = new Student(null, "Mike " + i, randomMark);
            studentDao.saveOrUpdate(st);
        }

        studentDao.saveOrUpdate(student);
        studentDao.delete(student);
        System.out.println(studentDao.findById(8L).toString());
        System.out.println(studentDao.findAll().toString());
    }
}
