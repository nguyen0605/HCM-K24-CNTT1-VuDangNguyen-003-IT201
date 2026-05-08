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

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) {
        return courseRepository.findById(id);
    }

    public void save(Course course) {
        courseRepository.save(course);
    }

    public void update(Course course) {
        courseRepository.update(course);
    }

    public void delete(Long id) {
        courseRepository.delete(id);
    }

    public List<Course> search(String keyword) {
        String lowerKeyword = keyword.toLowerCase();

        return courseRepository.findAll()
                .stream()
                .filter(c ->
                        c.getCourseName().toLowerCase().contains(lowerKeyword)
                                || c.getInstructor().toLowerCase().contains(lowerKeyword)
                )
                .collect(Collectors.toList());
    }
}