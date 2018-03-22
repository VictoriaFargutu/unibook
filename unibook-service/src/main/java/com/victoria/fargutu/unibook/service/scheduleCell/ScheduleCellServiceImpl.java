package com.victoria.fargutu.unibook.service.scheduleCell;

import com.victoria.fargutu.unibook.repository.commons.Day;
import com.victoria.fargutu.unibook.repository.db.ScheduleCellRepository;
import com.victoria.fargutu.unibook.repository.model.classroom.Classroom;
import com.victoria.fargutu.unibook.repository.model.schedulleCell.ScheduleCell;
import com.victoria.fargutu.unibook.repository.model.schedulleCell.ScheduleCellResponse;
import com.victoria.fargutu.unibook.repository.model.studentsGroup.StudentsGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleCellServiceImpl implements ScheduleCellService {
    private ScheduleCellRepository scheduleCellRepository;

    @Autowired
    public ScheduleCellServiceImpl(ScheduleCellRepository scheduleCellRepository) {
        this.scheduleCellRepository = scheduleCellRepository;
    }

    @Override
    public ScheduleCell createScheduleCell(ScheduleCell scheduleCell) {
        return scheduleCellRepository.save(scheduleCell);
    }

    @Override
    public List<ScheduleCellResponse> findAllByFreeHour(String hour) {
        List<ScheduleCell> scheduleCells = scheduleCellRepository.findAll();
        List<ScheduleCellResponse> scheduleCellResponses = new ArrayList<>();
        for (ScheduleCell scheduleCell : scheduleCells) {
            if (!scheduleCell.getHour().equals(hour)) {
                scheduleCells.remove(scheduleCell);
                if (scheduleCells.isEmpty()) {
                    break;
                }
            } else {
                scheduleCellResponses.add(new ScheduleCellResponse(scheduleCell));
            }
        }
        return scheduleCellResponses;
    }

    @Override
    public List<ScheduleCell> findAllByFreeDay(Day day) {
        return null;
    }

    @Override
    public List<ScheduleCell> findAllByFreeClassroom(Classroom classroom) {
        return null;
    }

    @Override
    public List<ScheduleCell> findAllByFreeStudentsGroup(StudentsGroup studentsGroup) {
        return null;
    }
}
