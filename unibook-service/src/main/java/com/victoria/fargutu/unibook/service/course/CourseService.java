package com.victoria.fargutu.unibook.service.course;

import com.victoria.fargutu.unibook.repository.model.course.Course;
import com.victoria.fargutu.unibook.repository.model.course.CourseResponse;

import java.util.List;

public interface CourseService {
    Course getCourseById(Long id);

    List<CourseResponse> getAll();

    List<Course> getCourses();
}
