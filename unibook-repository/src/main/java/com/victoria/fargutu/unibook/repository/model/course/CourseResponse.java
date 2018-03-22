package com.victoria.fargutu.unibook.repository.model.course;

public class CourseResponse {
    private Long id;
    private String name;

    public CourseResponse(Course course) {
        this.id = course.getId();
        this.name = course.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
