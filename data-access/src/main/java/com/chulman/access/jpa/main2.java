package com.chulman.access.jpa;

import com.chulman.access.jpa.course.Course;
import com.chulman.access.jpa.course.CourseConfiguration;
import com.chulman.access.jpa.course.CourseDao;
import com.chulman.access.jpa.course.HibernateCourseDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.GregorianCalendar;

public class main2 {
    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(CourseConfiguration.class) ;
        context.getBean(CourseDao.class);
    }
}
