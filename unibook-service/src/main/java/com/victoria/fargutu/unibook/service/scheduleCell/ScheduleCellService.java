package com.victoria.fargutu.unibook.service.scheduleCell;

import com.victoria.fargutu.unibook.repository.commons.Day;
import com.victoria.fargutu.unibook.repository.model.schedulleCell.ScheduleCell;
import com.victoria.fargutu.unibook.repository.model.schedulleCell.ScheduleCellResponse;

import java.util.List;

public interface ScheduleCellService {
    ScheduleCell createScheduleCell(ScheduleCell scheduleCell);

    List<ScheduleCellResponse> findAllByFreeHour(String hour);

    List<ScheduleCellResponse> findAllByFreeDay(Day day);

    List<ScheduleCellResponse> findAllByFreeClassroom(Long classroomId);

    List<ScheduleCellResponse> findAllByFreeStudentsGroup(Long studentsGroupId);
}
