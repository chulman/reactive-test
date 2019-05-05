package com.chulman.access.jpa.course;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.PostgreSQL9Dialect;

import javax.transaction.Transactional;
import java.util.List;

public class HibernateCourseDao implements CourseDao {

    private SessionFactory sessionFactory = null;


    public HibernateCourseDao() {
        Configuration configuration = new Configuration()
                .setProperty(AvailableSettings.URL, "jdbc:postgresql://localhost:5432/course")
                .setProperty(AvailableSettings.USER, "postgres")
                .setProperty(AvailableSettings.PASS, "password")
                .setProperty(AvailableSettings.DIALECT, PostgreSQL9Dialect.class.getName())
                .setProperty(AvailableSettings.SHOW_SQL, String.valueOf(true))
                .addAnnotatedClass(Course.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    //    @Override
//    public void store(Course course) {
//        Session session = sessionFactory.openSession();
//        Transaction tx = session.getTransaction();
//        try {
//            tx.begin();
//            session.saveOrUpdate(course);
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//            tx.rollback();
//            throw e;
//        } finally {
//            session.close();
//        }
//
//    }
    @Transactional
    @Override
    public void store(Course course) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(course);
    }

    @Override
    public void delete(Long courseId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Course course = session.get(Course.class, courseId);
            session.delete(course);

        } catch (RuntimeException e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Course findById(Long courseId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();

        Course course = null;
        try {
            tx.begin();
            course = session.get(Course.class, courseId);

        } catch (RuntimeException e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        return course;
    }

    @Override
    public List<Course> findAll() {
        Session session = sessionFactory.openSession();
        try {
            String query = "SELECT c from COURSE c";
            return session.createQuery(query, Course.class).list();
        } finally {
            session.close();
        }
    }
}
