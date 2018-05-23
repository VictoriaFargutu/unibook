package com.victoria.fargutu.unibook.repository.model.studentsGroup;

import com.victoria.fargutu.unibook.repository.commons.Specialization;

public class StudentsGroupResponse {
    private Long id;
    private String name;
    private String year;
    private Specialization specialization;

    public StudentsGroupResponse(StudentsGroup studentsGroup) {
        this.id = studentsGroup.getId();
        this.name = studentsGroup.getName();
        this.year = studentsGroup.getYear();
        this.specialization = studentsGroup.getSpecialization();
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
}
