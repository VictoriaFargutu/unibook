package com.victoria.fargutu.unibook.service.course;

import com.victoria.fargutu.unibook.repository.db.CourseRepository;
import com.victoria.fargutu.unibook.repository.model.course.Course;
import com.victoria.fargutu.unibook.repository.model.course.CourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findOne(id);
    }

    @Override
    public List<CourseResponse> getAll() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (Course course : courses) {
            courseResponses.add(new CourseResponse(course));
        }
        return courseResponses;
    }
}
