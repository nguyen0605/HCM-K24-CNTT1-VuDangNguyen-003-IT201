package com.re.coursemanagement.controller;

import com.re.coursemanagement.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public String list(
            @RequestParam(name = "keyword",required = false) String keyword,
            Model model
    ){
        if (keyword!=null && !keyword.isEmpty()){
            model.addAttribute("courses",courseService.search(keyword));
        } else {
            model.addAttribute("courses",courseService.findAll());
        }

        model.addAttribute("keyword", keyword);
        return "courses/list";
    }

    @GetMapping("/courses/create")
    public String add(){
        return "courses/form";
    }

}
