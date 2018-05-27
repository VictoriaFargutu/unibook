package com.victoria.fargutu.unibook.api.apis;

import com.victoria.fargutu.unibook.repository.commons.UserRole;
import com.victoria.fargutu.unibook.repository.model.course.CourseResponse;
import com.victoria.fargutu.unibook.service.course.CourseService;
import com.victoria.fargutu.unibook.service.security.HasRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/courses")
public class CourseApi {
    private CourseService courseService;

    public CourseApi(CourseService courseService) {
        this.courseService = courseService;
    }

    @HasRole(UserRole.USER)
    @GetMapping(value = "/all")
    public List<CourseResponse> getAll() {
        return courseService.getAll();
    }
}
