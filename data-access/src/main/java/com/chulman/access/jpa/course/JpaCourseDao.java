package com.chulman.access.jpa.course;

import javax.persistence.*;
import java.util.List;

public class JpaCourseDao implements CourseDao {

    private EntityManagerFactory entityManagerFactory;

    public JpaCourseDao(){
        entityManagerFactory = Persistence.createEntityManagerFactory("couese");
    }

    @Override
    public void store(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {
            tx.begin();
            entityManager.merge(course);
            tx.commit();

        } catch (RuntimeException e){
            tx.rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(Long courseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {
            tx.begin();
            Course course = entityManager.find(Course.class, courseId);
            entityManager.remove(course);
            tx.commit();

        } catch (RuntimeException e){
            tx.rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Course findById(Long courseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.find(Course.class,  courseId);

        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Course> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery("select course from Course course");
            return query.getResultList();

        } finally {
            entityManager.close();
        }
    }
}
