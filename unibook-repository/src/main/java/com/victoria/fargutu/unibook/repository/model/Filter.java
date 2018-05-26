package com.victoria.fargutu.unibook.repository.model;

import com.victoria.fargutu.unibook.repository.commons.*;
import com.victoria.fargutu.unibook.repository.model.classroom.Classroom;
import com.victoria.fargutu.unibook.repository.model.studentsGroup.StudentsGroup;

import java.util.Date;

public class Filter {
    private Classroom classroom;
    private ClassroomType classroomType;
    private WeekType weekType;
    private Day day;
    private Date date;
    private String hour;
    private String year;
    private Specialization specialization;
    private StudentsGroup studentsGroup;
    private Subgroup subgroup;

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public ClassroomType getClassroomType() {
        return classroomType;
    }

    public void setClassroomType(ClassroomType classroomType) {
        this.classroomType = classroomType;
    }

    public WeekType getWeekType() {
        return weekType;
    }

    public void setWeekType(WeekType weekType) {
        this.weekType = weekType;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
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

    public StudentsGroup getStudentsGroup() {
        return studentsGroup;
    }

    public void setStudentsGroup(StudentsGroup studentsGroup) {
        this.studentsGroup = studentsGroup;
    }

    public Subgroup getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(Subgroup subgroup) {
        this.subgroup = subgroup;
    }
}
