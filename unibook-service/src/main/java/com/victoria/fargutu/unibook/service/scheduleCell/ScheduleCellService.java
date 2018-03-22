package com.victoria.fargutu.unibook.service.scheduleCell;

import com.victoria.fargutu.unibook.repository.commons.Day;
import com.victoria.fargutu.unibook.repository.model.classroom.Classroom;
import com.victoria.fargutu.unibook.repository.model.schedulleCell.ScheduleCell;
import com.victoria.fargutu.unibook.repository.model.schedulleCell.ScheduleCellResponse;
import com.victoria.fargutu.unibook.repository.model.studentsGroup.StudentsGroup;

import java.util.List;

public interface ScheduleCellService {
    ScheduleCell createScheduleCell(ScheduleCell scheduleCell);

    List<ScheduleCellResponse> findAllByFreeHour(String hour);

    List<ScheduleCell> findAllByFreeDay(Day day);

    List<ScheduleCell> findAllByFreeClassroom(Classroom classroom);

    List<ScheduleCell> findAllByFreeStudentsGroup(StudentsGroup studentsGroup);
}
