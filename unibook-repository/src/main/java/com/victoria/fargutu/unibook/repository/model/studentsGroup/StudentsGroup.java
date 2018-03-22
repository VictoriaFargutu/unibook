package com.victoria.fargutu.unibook.repository.model.studentsGroup;

import com.victoria.fargutu.unibook.repository.model.schedulleCell.ScheduleCell;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class StudentsGroup {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String year;
    private String specialization;

    @OneToMany(mappedBy = "studentsGroup")
    private Set<ScheduleCell> scheduleCells;

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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Set<ScheduleCell> getScheduleCells() {
        return scheduleCells;
    }

    public void setScheduleCells(Set<ScheduleCell> scheduleCells) {
        this.scheduleCells = scheduleCells;
    }
}
