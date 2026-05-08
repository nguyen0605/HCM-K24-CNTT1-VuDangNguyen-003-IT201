package com.re.coursemanagement.model;

import jakarta.validation.constraints.*;

public class Course {
    private Long id;

    @NotBlank(message = "Tên khóa học không được để trống")
    @Size(min = 5, max = 100, message = "Tên khóa học từ 5-100 ký tự")
    private String courseName;

    @NotBlank(message = "Tên giảng viên không được để trống")
    @Size(min = 5, max = 100, message = "Tên giảng viên từ 5-100 ký tự")
    private String instructor;

    @NotNull(message = "Thời lượng khóa học không được để trống")
    @Min(value = 0, message = "Thời lượng khóa học phải lớn hơn 0")
    @Max(value = 500, message = "Thời lượng khóa học không quá 500 giờ")
    private Integer duration;

    @NotBlank(message = "Đường dẫn không được để trống")
    private String thumbnail;

    public Course() {
    }

    public Course(Integer duration, Long id, String courseName, String instructor, String thumbnail) {
        this.duration = duration;
        this.id = id;
        this.courseName = courseName;
        this.instructor = instructor;
        this.thumbnail = thumbnail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
