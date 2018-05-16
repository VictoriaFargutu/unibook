package com.victoria.fargutu.unibook.service.free_option.cell;

import com.victoria.fargutu.unibook.repository.commons.Day;
import com.victoria.fargutu.unibook.repository.commons.Hour;
import com.victoria.fargutu.unibook.repository.commons.WeekType;
import com.victoria.fargutu.unibook.repository.db.ClassroomRepository;
import com.victoria.fargutu.unibook.repository.db.FreeOptionCellRepository;
import com.victoria.fargutu.unibook.repository.db.ScheduleCellRepository;
import com.victoria.fargutu.unibook.repository.model.classroom.Classroom;
import com.victoria.fargutu.unibook.repository.model.free_option_cell.FreeOptionCell;
import com.victoria.fargutu.unibook.repository.model.schedulleCell.ScheduleCell;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FreeOptionCellServiceImpl implements FreeOptionCellService {
    private FreeOptionCellRepository freeOptionCellRepository;
    private ScheduleCellRepository scheduleCellRepository;
    private ClassroomRepository classroomRepository;

    public FreeOptionCellServiceImpl(FreeOptionCellRepository freeOptionCellRepository, ScheduleCellRepository scheduleCellRepository, ClassroomRepository classroomRepository) {
        this.freeOptionCellRepository = freeOptionCellRepository;
        this.scheduleCellRepository = scheduleCellRepository;
        this.classroomRepository = classroomRepository;
    }

    @Override
    public void addFreeOptioncells() {
//        List<WeekType> weekTypes = new ArrayList<>();
//        weekTypes.add(WeekType.ODD_WEEK);
//        weekTypes.add(WeekType.EVEN_WEEK);

        WeekType[] weekTypes = WeekType.values();
        Day[] days = Day.values();
        List<String> hours = new ArrayList<>();
        hours.add(Hour.EIGHT_TO_TEN);
        hours.add(Hour.TEN_TO_TWELVE);
        hours.add(Hour.TWELVE_TO_FOURTEEN);
        hours.add(Hour.FOURTEEN_TO_SIXTEEN);
        hours.add(Hour.SIXTEEN_TO_EIGHTEEN);
        hours.add(Hour.EIGHTEEN_TO_TWENTY);
        hours.add(Hour.TWENTY_TO_TWENTYTWO);

        List<ScheduleCell> scheduleCells = scheduleCellRepository.findAll();
        List<Classroom> classrooms = classroomRepository.findAll();

        for (WeekType weekType : weekTypes) {
            for (Day day : days) {
                for (String hour : hours) {
                    for (Classroom classroom : classrooms) {
                        for (ScheduleCell scheduleCell : scheduleCells) {
                            if (scheduleCell.getWeekType().equals(weekType) && scheduleCell.getDay().equals(day) && scheduleCell.getHour().equals(hour) && scheduleCell.getClassroom().getId().equals(classroom.getId())) {
                                break;
                            } else {
                                FreeOptionCell freeOptionCell = new FreeOptionCell();
                                freeOptionCell.setWeekType(weekType);
                                freeOptionCell.setDay(day);
                                freeOptionCell.setHour(hour);
                                freeOptionCell.setClassroom(classroom);
                                freeOptionCellRepository.save(freeOptionCell);
                            }
                        }
                    }
                }
            }
        }
    }
}