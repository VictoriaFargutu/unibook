package com.victoria.fargutu.unibook.service.course;

import com.victoria.fargutu.unibook.repository.db.CourseRepository;
import com.victoria.fargutu.unibook.repository.model.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
