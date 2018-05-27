package com.victoria.fargutu.unibook.repository.model.reservation;

import com.victoria.fargutu.unibook.repository.commons.Day;
import com.victoria.fargutu.unibook.repository.commons.WeekType;
import com.victoria.fargutu.unibook.repository.model.classroom.ClassroomResponse;
import com.victoria.fargutu.unibook.repository.model.course.CourseResponse;
import com.victoria.fargutu.unibook.repository.model.studentsGroup.StudentsGroupResponse;
import com.victoria.fargutu.unibook.repository.model.user.UserResponse;

import java.util.Date;

public class ReservationResponse {
    private Long id;
    private StudentsGroupResponse studentsGroup;
    private ClassroomResponse classroom;
    private CourseResponse course;
    private WeekType weekType;
    private Day day;
    private Date date;
    private String hour;
    private UserResponse user;

    public ReservationResponse(Reservation reservation) {
        this.id = reservation.getId();
        this.user = new UserResponse(reservation.getUser());
        if (reservation.getStudentsGroup() != null) {
            this.studentsGroup = new StudentsGroupResponse(reservation.getStudentsGroup());
        } else {
            this.studentsGroup = null;
        }
        if (reservation.getClassroom() != null) {
            this.classroom = new ClassroomResponse(reservation.getClassroom());
        } else {
            this.classroom = null;
        }
        if (reservation.getCourse() != null) {
            this.course = new CourseResponse(reservation.getCourse());
        } else {
            this.course = null;
        }
        this.weekType = reservation.getWeekType();
        this.day = reservation.getDay();
        this.date = reservation.getDate();
        this.hour = reservation.getHour();
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

    public ClassroomResponse getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassroomResponse classroom) {
        this.classroom = classroom;
    }

    public CourseResponse getCourse() {
        return course;
    }

    public void setCourse(CourseResponse course) {
        this.course = course;
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

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }
}
