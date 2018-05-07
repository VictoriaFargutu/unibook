package com.victoria.fargutu.unibook.repository.model.classroom;

import com.victoria.fargutu.unibook.repository.commons.ClassroomType;

public class ClassroomResponse {
    private Long id;
    private String name;
    private ClassroomType type;
    private int capacity;
    private boolean hasProjector;

    public ClassroomResponse(Classroom classroom) {
        this.id = classroom.getId();
        this.name = classroom.getName();
        this.type = classroom.getType();
        this.capacity = classroom.getCapacity();
        this.hasProjector = classroom.isHasProjector();
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
