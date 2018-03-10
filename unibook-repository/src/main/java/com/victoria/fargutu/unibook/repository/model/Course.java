package com.victoria.fargutu.unibook.repository.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "course_user",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> users;

    @OneToMany(mappedBy = "course")
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<ScheduleCell> getScheduleCells() {
        return scheduleCells;
    }

    public void setScheduleCells(Set<ScheduleCell> scheduleCells) {
        this.scheduleCells = scheduleCells;
    }
}
