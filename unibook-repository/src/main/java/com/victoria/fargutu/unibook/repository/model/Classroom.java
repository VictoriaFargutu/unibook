package com.victoria.fargutu.unibook.repository.model;

import com.victoria.fargutu.unibook.repository.commons.ClassroomType;

import javax.persistence.*;

@Entity
public class Classroom {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private ClassroomType type;

    private int capacity;
    private boolean hasProjector;

    public Long getId() {
        return id;
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
}