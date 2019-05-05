package com.chulman.access.jpa.course;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ## hibernate context session으로 객체 저장
 * - 하이버네이트 3 버전 부터 세션 팩토리가 컨텍스트 관리를 대신한다.
 * - 하나의 트랜잭션에서 getCurrentSession 메서드를 호출할 떄마다 동일한 세션을 얻을 수 있어서 트랜잭션당 정확히 하나의 하이버네이트 세션을 보장할 수 있다.
 *
 * - 컨텍셔스트 세션을 이용하려면 세션 팩토리에 접근 가능해야 한다.

 * - @Transactional을 모두 붙어야한다.
 * - 스프링은 자체적으로 하이버네이트 CurrentSessionContext 인터페이스 구현체를 갖고 있으며, 트랜잭션을 찾아보고 없으면 해당 스레드에 바인딩된 하이버네이트 세션이 발견되지 않았다고 정의
 *
 */

public class HibernateTransactionalCourseDao implements CourseDao {

    private SessionFactory sessionFactory = null;


    public HibernateTransactionalCourseDao(SessionFactory sessoinFactroy) {
        this.sessionFactory = sessoinFactroy;
    }

    @Transactional
    @Override
    public void store(Course course) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(course);
    }

    @Transactional
    @Override
    public void delete(Long courseId) {
        Session session = sessionFactory.getCurrentSession();
        Course course = session.get(Course.class, courseId);
        session.delete(course);
    }

    @Transactional(readOnly = true)
    @Override
    public Course findById(Long courseId) {
        Session session = sessionFactory.getCurrentSession();
        Course course = session.get(Course.class, courseId);
        return course;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> findAll() {
        Session session = sessionFactory.getCurrentSession();
        String query = "SELECT c from COURSE c";
        return session.createQuery(query, Course.class).list();
    }
}
