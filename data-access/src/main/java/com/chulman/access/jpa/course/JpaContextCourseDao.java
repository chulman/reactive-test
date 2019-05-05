package com.chulman.access.jpa.course;


import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class JpaContextCourseDao implements CourseDao {

    // 엔티티 관리자 주입
    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    @Override
    public void store(Course course) {
        entityManager.merge(course);
    }

    @Transactional
    @Override
    public void delete(Long courseId) {
        Course course = entityManager.find(Course.class, courseId);
        entityManager.remove(course);
    }


    @Transactional(readOnly = true)
    @Override
    public Course findById(Long courseId) {
        return entityManager.find(Course.class, courseId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> findAll() {
        Query query = entityManager.createQuery("select course from Course course");
        return query.getResultList();
    }
}
