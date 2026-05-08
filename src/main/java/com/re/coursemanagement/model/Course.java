package com.re.coursemanagement.model;

import jakarta.validation.constraints.*;

public class Course {

    private Long id;

    @NotBlank(message = "Tên khóa học không được để trống")
    @Size(min = 5, max = 100, message = "Tên khóa học phải từ 5 đến 100 ký tự")
    private String courseName;

    @NotBlank(message = "Giảng viên không được để trống")
    private String instructor;

    @NotNull(message = "Thời lượng không được để trống")
    @Min(value = 1, message = "Thời lượng phải lớn hơn 0")
    @Max(value = 500, message = "Thời lượng không được quá 500 giờ")
    private Integer duration;

    private String thumbnail;

    public Course() {
    }

    public Course(Long id, String courseName, String instructor, Integer duration, String thumbnail) {
        this.id = id;
        this.courseName = courseName;
        this.instructor = instructor;
        this.duration = duration;
        this.thumbnail = thumbnail;
    }

    public Long getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}