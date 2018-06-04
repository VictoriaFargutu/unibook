package com.victoria.fargutu.unibook.service.schedule;

import com.victoria.fargutu.unibook.repository.db.ScheduleRepository;
import com.victoria.fargutu.unibook.repository.model.schedule.Schedule;
import com.victoria.fargutu.unibook.service.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule createSchedule(Schedule schedule) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(schedule.getSemesterStartDate());

        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.SECOND, 10);
        calendar.set(Calendar.MILLISECOND, 10);
        schedule.setSemesterStartDate(calendar.getTime());
        Schedule saved = new Schedule();

        Schedule existingSchedule = scheduleRepository.findByFaculty_IdAndSemester(schedule.getFaculty().getId(), schedule.getSemester());
        if (existingSchedule != null) {
            Calendar tempCalendar = Calendar.getInstance();
            tempCalendar.setTime(existingSchedule.getSemesterStartDate());
            if (calendar.get(Calendar.YEAR) == tempCalendar.get(Calendar.YEAR) && !existingSchedule.getSemesterStartDate().equals(schedule.getSemesterStartDate())) {
                existingSchedule.setSemesterStartDate(schedule.getSemesterStartDate());
                saved = scheduleRepository.save(existingSchedule);
            } else if (calendar.get(Calendar.YEAR) != tempCalendar.get(Calendar.YEAR)) {
                saved = scheduleRepository.save(schedule);
            }
        } else {
            saved = scheduleRepository.save(schedule);
        }
        return saved;
    }

    @Override
    public Schedule getSchedule(Schedule schedule) {
        Schedule schedule1 = scheduleRepository.findOne(schedule.getId());
        if (schedule1 == null) {
            throw new NotFoundException("Schedule not found!");
        }
        return schedule1;
    }
}
