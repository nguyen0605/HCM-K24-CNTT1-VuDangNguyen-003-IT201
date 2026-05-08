package com.re.coursemanagement.service;

import com.re.coursemanagement.model.Course;
import com.re.coursemanagement.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    public List<Course> search(String keyword){
        return courseRepository.findAll()
                .stream()
                .filter(course -> course.getCourseName().toLowerCase().contains(keyword.toLowerCase())
                        || course.getInstructor().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void save(Course course) {
        courseRepository.save(course);
    }
}
