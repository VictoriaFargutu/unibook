package com.victoria.fargutu.unibook.repository.model.schedule;

import com.victoria.fargutu.unibook.repository.commons.Semester;
import com.victoria.fargutu.unibook.repository.model.Faculty.Faculty;
import com.victoria.fargutu.unibook.repository.model.schedulleCell.ScheduleCell;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @OneToMany(mappedBy = "schedule")
    private Set<ScheduleCell> scheduleCells;

    public Long getId() {
        return id;
    }

    public Set<ScheduleCell> getScheduleCells() {
        return scheduleCells;
    }

    public void setScheduleCells(Set<ScheduleCell> scheduleCells) {
        this.scheduleCells = scheduleCells;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
