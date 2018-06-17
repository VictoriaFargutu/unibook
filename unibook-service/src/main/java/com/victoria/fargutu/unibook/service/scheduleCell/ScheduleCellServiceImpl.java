package com.victoria.fargutu.unibook.service.scheduleCell;

import com.victoria.fargutu.unibook.repository.commons.Day;
import com.victoria.fargutu.unibook.repository.commons.Subgroup;
import com.victoria.fargutu.unibook.repository.db.ScheduleCellRepository;
import com.victoria.fargutu.unibook.repository.db.ScheduleRepository;
import com.victoria.fargutu.unibook.repository.model.scheduleCell.ScheduleCell;
import com.victoria.fargutu.unibook.repository.model.scheduleCell.ScheduleCellResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleCellServiceImpl implements ScheduleCellService {
    private ScheduleCellRepository scheduleCellRepository;
    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleCellServiceImpl(ScheduleCellRepository scheduleCellRepository, ScheduleRepository scheduleRepository) {
        this.scheduleCellRepository = scheduleCellRepository;
        this.scheduleRepository = scheduleRepository;
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
            if (scheduleCell.getHour().equals(hour)) {
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
    public List<ScheduleCellResponse> findAllByFreeDay(Day day) {
        List<ScheduleCell> scheduleCells = scheduleCellRepository.findAll();
        List<ScheduleCellResponse> scheduleCellResponses = new ArrayList<>();
        for (ScheduleCell scheduleCell : scheduleCells) {
            if (scheduleCell.getDay().equals(day)) {
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
    public List<ScheduleCellResponse> findAllByFreeClassroom(Long classroomId) {
        List<ScheduleCell> scheduleCells = scheduleCellRepository.findAll();
        List<ScheduleCellResponse> scheduleCellResponses = new ArrayList<>();
        for (ScheduleCell scheduleCell : scheduleCells) {
            if (scheduleCell.getClassroom().getId().equals(classroomId)) {
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
    public List<ScheduleCellResponse> findAllByFreeStudentsGroup(Long studentsGroupId) {
        List<ScheduleCell> scheduleCells = scheduleCellRepository.findAll();
        List<ScheduleCellResponse> scheduleCellResponses = new ArrayList<>();
        for (ScheduleCell scheduleCell : scheduleCells) {
            if (scheduleCell.getStudentsGroup().getId().equals(studentsGroupId)) {
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
}
