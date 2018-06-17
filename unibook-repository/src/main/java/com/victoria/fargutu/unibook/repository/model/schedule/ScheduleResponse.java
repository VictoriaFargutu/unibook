package com.victoria.fargutu.unibook.repository.model.schedule;

import com.victoria.fargutu.unibook.repository.commons.Semester;
import com.victoria.fargutu.unibook.repository.model.faculty.FacultyResponse;

public class ScheduleResponse {
    private Long id;
    private Semester semester;
    private FacultyResponse faculty;

    public ScheduleResponse(Schedule schedule) {
        this.id = schedule.getId();
        this.semester = schedule.getSemester();
        this.faculty = new FacultyResponse(schedule.getFaculty());
    }

    public Long getId() {
        return id;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public FacultyResponse getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyResponse faculty) {
        this.faculty = faculty;
    }
}
