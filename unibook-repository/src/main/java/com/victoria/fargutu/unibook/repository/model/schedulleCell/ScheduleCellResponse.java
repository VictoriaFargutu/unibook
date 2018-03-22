package com.victoria.fargutu.unibook.repository.model.schedulleCell;

import com.victoria.fargutu.unibook.repository.commons.Day;
import com.victoria.fargutu.unibook.repository.commons.Subgroup;
import com.victoria.fargutu.unibook.repository.commons.WeekType;
import com.victoria.fargutu.unibook.repository.model.classroom.Classroom;
import com.victoria.fargutu.unibook.repository.model.course.Course;
import com.victoria.fargutu.unibook.repository.model.schedule.Schedule;
import com.victoria.fargutu.unibook.repository.model.studentsGroup.StudentsGroup;

public class ScheduleCellResponse {

    private Long id;
    private StudentsGroup studentsGroup;
    private Subgroup subgroup;
    private WeekType weekType;
    private String hour;
    private Day day;
    private Long userId;
    private Course course;
    private Classroom classroom;
    private Schedule schedule;

    public ScheduleCellResponse(ScheduleCell scheduleCell) {
        this.id = scheduleCell.getId();
        this.studentsGroup = scheduleCell.getStudentsGroup();
        this.subgroup = scheduleCell.getSubgroup();
        this.weekType = scheduleCell.getWeekType();
        this.hour = scheduleCell.getHour();
        this.day = scheduleCell.getDay();
        this.userId = scheduleCell.getUser().getId();
        this.course = scheduleCell.getCourse();
        this.classroom = scheduleCell.getClassroom();
        this.schedule = scheduleCell.getSchedule();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public WeekType getWeekType() {
        return weekType;
    }

    public void setWeekType(WeekType weekType) {
        this.weekType = weekType;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
