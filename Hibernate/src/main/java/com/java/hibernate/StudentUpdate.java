package com.java.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class StudentUpdate {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Student student = session.get(Student.class, 1111);
        Query query = session.createQuery("UPDATE Student SET marks=:marks where sname=:sname");
        query.setParameter("marks", 3.4);
        query.setParameter("sname", "Olvadis");
        query.executeUpdate();
        //student.setMarks(4.9);
        //session.merge(student);
        tx.commit();
        session.close();
    }
}
