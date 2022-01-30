package com.petrov.dao;

import com.petrov.Config;
import com.petrov.persist.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDaoImpl implements StudentDao{

    private Session session;

    private Transaction transaction;

    public Session openSession() {
        session = Config.getSessionFactory().openSession();
        return session;
    }

    public Session openSessionWithTransaction() {
        session = Config.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public void closeSession() {
        session.close();
    }

    public void closeSessionwithTransaction() {
        transaction.commit();
        session.close();
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public List findAll() {
        return session.createQuery("select s from Student s").list();
    }

    @Override
    public Student findById(Long id) {
        return (Student) session.get(Student.class, id);
    }

    @Override
    public void saveOrUpdate(Student student) {
        session.saveOrUpdate(student);
    }

    @Override
    public void delete(Student student) {
        session.delete(student);
    }
}
