package com.victoria.fargutu.unibook.service.scheduleCell;

import com.victoria.fargutu.unibook.repository.commons.Day;
import com.victoria.fargutu.unibook.repository.db.ScheduleCellRepository;
import com.victoria.fargutu.unibook.repository.db.ScheduleRepository;
import com.victoria.fargutu.unibook.repository.model.ScheduleMap;
import com.victoria.fargutu.unibook.repository.model.schedule.Schedule;
import com.victoria.fargutu.unibook.repository.model.scheduleCell.ScheduleCell;
import com.victoria.fargutu.unibook.repository.model.scheduleCell.ScheduleCellResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
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
    public ScheduleCell createScheduleCell(ScheduleMap scheduleMap) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(scheduleMap.getSemesterStartDate());

        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.SECOND, 10);
        calendar.set(Calendar.MILLISECOND, 10);

        Schedule schedule = scheduleRepository.findByFaculty_IdAndSemester(scheduleMap.getFaculty().getId(), scheduleMap.getSemester());

        if (schedule == null) {
            schedule = new Schedule();
            schedule.setFaculty(scheduleMap.getFaculty());
            schedule.setSemester(scheduleMap.getSemester());
            schedule.setSemesterStartDate(calendar.getTime());
            scheduleRepository.save(schedule);
        }

        ScheduleCell scheduleCell = new ScheduleCell();
        scheduleCell.setClassroom(scheduleMap.getClassroom());
        scheduleCell.setSchedule(schedule);
        scheduleCell.setCourse(scheduleMap.getCourse());
        scheduleCell.setDay(scheduleMap.getDay());
        scheduleCell.setHour(scheduleMap.getHour());
        scheduleCell.setUser(scheduleMap.getUser());
        scheduleCell.setStudentsGroup(scheduleMap.getStudentsGroup());
        scheduleCell.setWeekType(scheduleMap.getWeekType());
        scheduleCell.setSubgroup(scheduleMap.getSubgroup());

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
