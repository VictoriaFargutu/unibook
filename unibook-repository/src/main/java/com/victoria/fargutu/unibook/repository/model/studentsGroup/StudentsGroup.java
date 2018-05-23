package com.victoria.fargutu.unibook.repository.model.studentsGroup;

import com.victoria.fargutu.unibook.repository.commons.Specialization;
import com.victoria.fargutu.unibook.repository.model.reservation.Reservation;
import com.victoria.fargutu.unibook.repository.model.schedulleCell.ScheduleCell;

import javax.persistence.*;
import java.util.Set;

@Entity
public class StudentsGroup {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String year;

    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    @OneToMany(mappedBy = "studentsGroup")
    private Set<ScheduleCell> scheduleCells;

    @OneToMany(mappedBy = "studentsGroup")
    private Set<Reservation> reservations;

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

    public Set<ScheduleCell> getScheduleCells() {
        return scheduleCells;
    }

    public void setScheduleCells(Set<ScheduleCell> scheduleCells) {
        this.scheduleCells = scheduleCells;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
