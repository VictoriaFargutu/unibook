package com.victoria.fargutu.unibook.repository.model.free_option;

import com.victoria.fargutu.unibook.repository.commons.Day;
import com.victoria.fargutu.unibook.repository.commons.Specialization;
import com.victoria.fargutu.unibook.repository.commons.Subgroup;
import com.victoria.fargutu.unibook.repository.commons.WeekType;
import com.victoria.fargutu.unibook.repository.model.classroom.ClassroomResponse;
import com.victoria.fargutu.unibook.repository.model.studentsGroup.StudentsGroupResponse;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FreeOption {
    private ClassroomResponse classroom;
    private WeekType weekType;
    private Date date;
    private Day day;
    private String hour;
    private String year;
    private Specialization specialization;
    private StudentsGroupResponse studentsGroup;
    private Subgroup subgroup;

    public ClassroomResponse getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassroomResponse classroom) {
        this.classroom = classroom;
    }

    public WeekType getWeekType() {
        return weekType;
    }

    public void setWeekType(WeekType weekType) {
        this.weekType = weekType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
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

    public StudentsGroupResponse getStudentsGroup() {
        return studentsGroup;
    }

    public void setStudentsGroup(StudentsGroupResponse studentsGroup) {
        this.studentsGroup = studentsGroup;
    }

    public Subgroup getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(Subgroup subgroup) {
        this.subgroup = subgroup;
    }
}
