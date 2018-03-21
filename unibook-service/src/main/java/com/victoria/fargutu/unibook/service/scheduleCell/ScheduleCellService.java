package com.victoria.fargutu.unibook.service.scheduleCell;

import com.victoria.fargutu.unibook.repository.commons.Day;
import com.victoria.fargutu.unibook.repository.commons.Hour;
import com.victoria.fargutu.unibook.repository.model.Classroom;
import com.victoria.fargutu.unibook.repository.model.schedule.ScheduleCell;
import com.victoria.fargutu.unibook.repository.model.schedule.ScheduleCellResponse;
import com.victoria.fargutu.unibook.repository.model.schedule.StudentsGroup;

import java.util.List;

public interface ScheduleCellService {
    ScheduleCell createScheduleCell(ScheduleCell scheduleCell);

    List<ScheduleCellResponse> findAllByFreeHour(String hour);

    List<ScheduleCell> findAllByFreeDay(Day day);

    List<ScheduleCell> findAllByFreeClassroom(Classroom classroom);

    List<ScheduleCell> findAllByFreeStudentsGroup(StudentsGroup studentsGroup);
}
