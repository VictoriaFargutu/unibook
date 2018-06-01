package com.victoria.fargutu.unibook.service.scheduleCell;

import com.victoria.fargutu.unibook.repository.commons.Day;
import com.victoria.fargutu.unibook.repository.model.ScheduleMap;
import com.victoria.fargutu.unibook.repository.model.scheduleCell.ScheduleCell;
import com.victoria.fargutu.unibook.repository.model.scheduleCell.ScheduleCellResponse;

import java.util.List;

public interface ScheduleCellService {
    ScheduleCell createScheduleCell(ScheduleMap scheduleMap);

    List<ScheduleCellResponse> findAllByFreeHour(String hour);

    List<ScheduleCellResponse> findAllByFreeDay(Day day);

    List<ScheduleCellResponse> findAllByFreeClassroom(Long classroomId);

    List<ScheduleCellResponse> findAllByFreeStudentsGroup(Long studentsGroupId);
}
