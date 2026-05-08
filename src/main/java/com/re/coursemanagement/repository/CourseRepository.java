package com.re.coursemanagement.repository;

import com.re.coursemanagement.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public CourseRepository(){
        courses.add(new Course(
                40,
                1L,
                "Lập trình java",
                "Dương Phúc Thịnh",
                "temp"
        ));
        courses.add(new Course(
                30,
                2L,
                "Thiết kế web",
                "Nguyễn Lê Vũ",
                "temp"
        ));
    }

    public List<Course> findAll(){
        return courses;
    }

    public Course save(Course course){
        return new Course();
    }

}
