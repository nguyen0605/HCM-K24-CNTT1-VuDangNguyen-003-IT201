package com.re.coursemanagement.repository;

import com.re.coursemanagement.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CourseRepository {

    private final List<Course> courses = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(5);

    public CourseRepository() {
        courses.add(new Course(1L, "Lập trình Java", "Nguyễn Văn An", 60, null));
        courses.add(new Course(2L, "Thiết kế Web", "Trần Thị Bình", 45, null));
        courses.add(new Course(3L, "Cơ sở dữ liệu", "Lê Minh Cường", 50, null));
        courses.add(new Course(4L, "Spring MVC cơ bản", "Phạm Thu Dung", 70, null));
        courses.add(new Course(5L, "HTML CSS JavaScript", "Hoàng Đức Em", 40, null));
    }

    public List<Course> findAll() {
        return courses;
    }

    public Course findById(Long id) {
        return courses.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void save(Course course) {
        course.setId(idGenerator.incrementAndGet());
        courses.add(course);
    }

    public void update(Course course) {
        Course oldCourse = findById(course.getId());

        if (oldCourse != null) {
            oldCourse.setCourseName(course.getCourseName());
            oldCourse.setInstructor(course.getInstructor());
            oldCourse.setDuration(course.getDuration());
            oldCourse.setThumbnail(course.getThumbnail());
        }
    }

    public void delete(Long id) {
        courses.removeIf(c -> c.getId().equals(id));
    }
}