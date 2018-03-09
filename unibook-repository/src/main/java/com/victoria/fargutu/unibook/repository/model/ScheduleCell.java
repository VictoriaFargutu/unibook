package com.victoria.fargutu.unibook.repository.model;

import com.victoria.fargutu.unibook.repository.commons.Day;
import com.victoria.fargutu.unibook.repository.commons.Hour;
import com.victoria.fargutu.unibook.repository.commons.Subgroup;
import com.victoria.fargutu.unibook.repository.commons.WeekType;

import javax.persistence.*;

@Entity
public class ScheduleCell {
    @Id
    @GeneratedValue
    Long id;

    private Group group;

    @Enumerated(EnumType.STRING)
    private Subgroup subgroup;

    @Enumerated(EnumType.STRING)
    private WeekType weekType;

    @Enumerated(EnumType.STRING)
    private Hour hour;

    @Enumerated(EnumType.STRING)
    private Day day;

    private User user;//?

    private Course course;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
