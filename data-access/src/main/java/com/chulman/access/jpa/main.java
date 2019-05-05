package com.chulman.access.jpa;

import com.chulman.access.jpa.course.Course;
import com.chulman.access.jpa.course.CourseDao;
import com.chulman.access.jpa.course.HibernateCourseDao;

import java.util.GregorianCalendar;

public class main {
    public static void main(String[] args){
        CourseDao courseDao = new HibernateCourseDao();

        Course course = new Course();
        course.setTitle("title");
        course.setBeginDate(new GregorianCalendar(2007,8,1).getTime());
        course.setEndDate(new GregorianCalendar(2007,10,1).getTime());
        course.setFee(1000);

        courseDao.store(course);

    }
}
