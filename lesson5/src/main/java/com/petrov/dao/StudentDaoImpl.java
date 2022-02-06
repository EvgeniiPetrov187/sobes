package com.petrov.dao;

import com.petrov.Config;
import com.petrov.persist.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class StudentDaoImpl implements StudentDao{

    private Session session;

    private Transaction transaction;

    public Session openSessionWithTransaction() {
        session = Config.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public void closeSessionWithTransaction() {
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
        return executeSessionFunction(session -> session.createQuery("select s from Student s").list());
    }

    @Override
    public Optional<Student> findById(Long id) {
        return executeSessionFunction(session -> Optional.ofNullable((Student) session.get(Student.class, id)));
    }

    @Override
    public void saveOrUpdate(Student student) {
        executeSessionConsumer(session1 -> session1.saveOrUpdate(student));
    }

    @Override
    public void delete(Student student) {
        executeSessionConsumer(session -> session.delete(student));
    }

    private <T> T executeSessionFunction(Function<Session, T> function){
        try {
            return function.apply(openSessionWithTransaction());
        } finally {
            closeSessionWithTransaction();
        }
    }

    private <T> void executeSessionConsumer(Consumer<Session> consumer){
        try {
            consumer.accept(openSessionWithTransaction());
        } finally {
            closeSessionWithTransaction();
        }
    }
}
