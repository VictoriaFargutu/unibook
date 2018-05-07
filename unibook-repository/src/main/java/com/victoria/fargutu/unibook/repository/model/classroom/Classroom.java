package com.victoria.fargutu.unibook.repository.model.classroom;

import com.victoria.fargutu.unibook.repository.commons.ClassroomType;
import com.victoria.fargutu.unibook.repository.model.reservation.Reservation;
import com.victoria.fargutu.unibook.repository.model.schedulleCell.ScheduleCell;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Classroom {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ClassroomType type;

    private int capacity;
    private boolean hasProjector;

    @OneToMany(mappedBy = "classroom")
    private Set<ScheduleCell> scheduleCells;

    @OneToMany(mappedBy = "classroom")
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

    public ClassroomType getType() {
        return type;
    }

    public void setType(ClassroomType type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
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
