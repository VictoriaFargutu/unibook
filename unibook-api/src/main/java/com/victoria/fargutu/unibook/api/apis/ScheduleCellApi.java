package com.victoria.fargutu.unibook.api.apis;

import com.victoria.fargutu.unibook.repository.model.schedulleCell.ScheduleCell;
import com.victoria.fargutu.unibook.repository.model.schedulleCell.ScheduleCellResponse;
import com.victoria.fargutu.unibook.service.scheduleCell.ScheduleCellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/schedule/cell")
public class ScheduleCellApi {
    private ScheduleCellService scheduleCellService;

    @Autowired
    public ScheduleCellApi(ScheduleCellService scheduleCellService) {
        this.scheduleCellService = scheduleCellService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ScheduleCell createScheduleCell(@RequestBody ScheduleCell scheduleCell) {
        return scheduleCellService.createScheduleCell(scheduleCell);
    }

    @RequestMapping(value = "/hour", method = RequestMethod.GET)
    public List<ScheduleCellResponse> getAllByFreeHour(@RequestParam String hour) {
        return scheduleCellService.findAllByFreeHour(hour);
    }

}
