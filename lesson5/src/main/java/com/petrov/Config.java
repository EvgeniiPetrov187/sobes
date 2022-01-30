package com.petrov;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Config {
    private static final Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
    private static final SessionFactory sessionFactory = configuration.buildSessionFactory();

    public static SessionFactory getSessionFactory(){
        return sessionFactory;

    }
}
