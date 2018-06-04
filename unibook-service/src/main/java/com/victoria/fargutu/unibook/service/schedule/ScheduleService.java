package com.victoria.fargutu.unibook.service.schedule;

import com.victoria.fargutu.unibook.repository.model.schedule.Schedule;

public interface ScheduleService {
    Schedule createSchedule(Schedule schedule);
    Schedule getSchedule(Schedule schedule);
}
