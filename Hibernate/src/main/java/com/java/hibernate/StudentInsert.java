package com.java.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudentInsert {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Student student = new Student();
        student.setSno(1111);
        student.setSname("Olvadis");
        student.setMarks(3.5);
        session.persist(student);
        tx.commit();
        session.close();
    }
}
