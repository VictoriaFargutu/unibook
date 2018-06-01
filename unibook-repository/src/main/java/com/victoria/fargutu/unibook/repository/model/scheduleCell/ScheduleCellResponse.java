package com.victoria.fargutu.unibook.repository.model.scheduleCell;

import com.victoria.fargutu.unibook.repository.commons.Day;
import com.victoria.fargutu.unibook.repository.commons.Subgroup;
import com.victoria.fargutu.unibook.repository.commons.WeekType;
import com.victoria.fargutu.unibook.repository.model.classroom.ClassroomResponse;
import com.victoria.fargutu.unibook.repository.model.course.CourseResponse;
import com.victoria.fargutu.unibook.repository.model.schedule.ScheduleResponse;
import com.victoria.fargutu.unibook.repository.model.studentsGroup.StudentsGroupResponse;

public class ScheduleCellResponse {

    private Long id;
    private StudentsGroupResponse studentsGroup;
    private Subgroup subgroup;
    private WeekType weekType;
    private String hour;
    private Day day;
    private Long userId;
    private CourseResponse course;
    private ClassroomResponse classroom;
    private ScheduleResponse schedule;

    public ScheduleCellResponse(ScheduleCell scheduleCell) {
        this.id = scheduleCell.getId();
        this.studentsGroup = new StudentsGroupResponse(scheduleCell.getStudentsGroup());
        this.subgroup = scheduleCell.getSubgroup();
        this.weekType = scheduleCell.getWeekType();
        this.hour = scheduleCell.getHour();
        this.day = scheduleCell.getDay();
        this.userId = scheduleCell.getUser().getId();
        this.course = new CourseResponse(scheduleCell.getCourse());
        this.classroom = new ClassroomResponse(scheduleCell.getClassroom());
        this.schedule = new ScheduleResponse(scheduleCell.getSchedule());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CourseResponse getCourse() {
        return course;
    }

    public void setCourse(CourseResponse course) {
        this.course = course;
    }

    public ClassroomResponse getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassroomResponse classroom) {
        this.classroom = classroom;
    }

    public ScheduleResponse getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleResponse schedule) {
        this.schedule = schedule;
    }
}
