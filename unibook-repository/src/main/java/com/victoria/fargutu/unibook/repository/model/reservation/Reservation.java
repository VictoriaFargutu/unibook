package com.victoria.fargutu.unibook.repository.model.reservation;

import com.victoria.fargutu.unibook.repository.commons.Day;
import com.victoria.fargutu.unibook.repository.commons.Hour;
import com.victoria.fargutu.unibook.repository.commons.Subgroup;
import com.victoria.fargutu.unibook.repository.commons.WeekType;
import com.victoria.fargutu.unibook.repository.model.classroom.Classroom;
import com.victoria.fargutu.unibook.repository.model.course.Course;
import com.victoria.fargutu.unibook.repository.model.studentsGroup.StudentsGroup;
import com.victoria.fargutu.unibook.repository.model.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "studentsGroup_id")
    private StudentsGroup studentsGroup;

    @Enumerated(EnumType.STRING)
    private Subgroup subgroup;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Enumerated(EnumType.STRING)
    private WeekType weekType;

    @Enumerated(EnumType.STRING)
    private Day day;

    private Date date;

    private String hour;

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
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
}
