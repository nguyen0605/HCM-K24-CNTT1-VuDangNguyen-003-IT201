package com.re.coursemanagement.controller;

import com.re.coursemanagement.model.Course;
import com.re.coursemanagement.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    private final String uploadDir = "C:/course-uploads/";

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String list(
            @RequestParam(name = "keyword", required = false) String keyword,
            Model model
    ) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("courses", courseService.search(keyword));
        } else {
            model.addAttribute("courses", courseService.findAll());
        }

        model.addAttribute("keyword", keyword);
        return "courses/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("course", new Course());
        return "courses/form";
    }

    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("course") Course course,
            BindingResult result,
            @RequestParam(name = "imageFile", required = false) MultipartFile imageFile
    ) throws IOException {

        if (result.hasErrors()) {
            return "courses/form";
        }

        String fileName = saveImage(imageFile);

        if (fileName != null) {
            course.setThumbnail(fileName);
        }

        courseService.save(course);

        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Course course = courseService.findById(id);
        model.addAttribute("course", course);
        return "courses/form";
    }

    @PostMapping("/update")
    public String update(
            @Valid @ModelAttribute("course") Course course,
            BindingResult result,
            @RequestParam(name = "imageFile", required = false) MultipartFile imageFile
    ) {

        if (result.hasErrors()) {
            return "courses/form";
        }

        Course oldCourse = courseService.findById(course.getId());

        if (oldCourse == null) {
            return "redirect:/courses";
        }

        try {
            String fileName = saveImage(imageFile);

            if (fileName != null) {
                course.setThumbnail(fileName);
            } else {
                course.setThumbnail(oldCourse.getThumbnail());
            }

            courseService.update(course);

        } catch (Exception e) {
            e.printStackTrace();
            return "courses/form";
        }

        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        courseService.delete(id);
        return "redirect:/courses";
    }

    private String saveImage(MultipartFile imageFile) throws IOException {
        if (imageFile == null || imageFile.isEmpty()) {
            return null;
        }

        String originalName = imageFile.getOriginalFilename();

        if (originalName == null) {
            return null;
        }

        String lowerName = originalName.toLowerCase();

        if (!lowerName.endsWith(".jpg") && !lowerName.endsWith(".png")) {
            return null;
        }

        File folder = new File(uploadDir);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + originalName;
        File destination = new File(uploadDir + fileName);

        imageFile.transferTo(destination);

        return fileName;
    }
}