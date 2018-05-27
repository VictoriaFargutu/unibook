package com.victoria.fargutu.unibook.api.apis;

import com.victoria.fargutu.unibook.repository.commons.UserRole;
import com.victoria.fargutu.unibook.repository.model.schedule.Schedule;
import com.victoria.fargutu.unibook.service.schedule.ScheduleService;
import com.victoria.fargutu.unibook.service.security.HasRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/schedule")
public class ScheduleApi {
    private ScheduleService scheduleService;

    @Autowired
    public ScheduleApi(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @HasRole(UserRole.ADMIN)
    @RequestMapping(method = RequestMethod.POST)
    public Schedule createSchedule(@RequestBody Schedule schedule) {
        return scheduleService.createSchedule(schedule);
    }

}
