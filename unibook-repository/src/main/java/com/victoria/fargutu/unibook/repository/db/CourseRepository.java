package com.victoria.fargutu.unibook.repository.db;

import com.victoria.fargutu.unibook.repository.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
