package com.victoria.fargutu.unibook.repository.model.faculty;

public class FacultyResponse {
    private Long id;

    private String name;

    public FacultyResponse(Faculty faculty) {
        this.id = faculty.getId();
        this.name = faculty.getName();
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
