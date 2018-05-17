package com.victoria.fargutu.unibook.service.free_option;

import com.victoria.fargutu.unibook.repository.commons.Day;
import com.victoria.fargutu.unibook.repository.commons.Semester;
import com.victoria.fargutu.unibook.repository.commons.WeekType;
import com.victoria.fargutu.unibook.repository.db.ClassroomRepository;
import com.victoria.fargutu.unibook.repository.db.FreeOptionCellRepository;
import com.victoria.fargutu.unibook.repository.db.ScheduleCellRepository;
import com.victoria.fargutu.unibook.repository.db.ScheduleRepository;
import com.victoria.fargutu.unibook.repository.model.classroom.Classroom;
import com.victoria.fargutu.unibook.repository.model.classroom.ClassroomResponse;
import com.victoria.fargutu.unibook.repository.model.free_option.FreeOption;
import com.victoria.fargutu.unibook.repository.model.free_option_cell.FreeOptionCell;
import com.victoria.fargutu.unibook.repository.model.schedule.Schedule;
import com.victoria.fargutu.unibook.service.exceptions.NotFoundException;
import org.joda.time.DateTime;
import org.joda.time.Weeks;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class FreeOptionServiceImpl implements FreeOptionService {
    private ScheduleCellRepository scheduleCellRepository;
    private ClassroomRepository classroomRepository;
    private FreeOptionCellRepository freeOptionCellRepository;
    private ScheduleRepository scheduleRepository;

    public FreeOptionServiceImpl(ScheduleCellRepository scheduleCellRepository, ClassroomRepository classroomRepository, FreeOptionCellRepository freeOptionCellRepository, ScheduleRepository scheduleRepository) {
        this.scheduleCellRepository = scheduleCellRepository;
        this.classroomRepository = classroomRepository;
        this.freeOptionCellRepository = freeOptionCellRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<FreeOption> getAllFreeOptionsByClassroom(Long classroomId) {
        Classroom classroom = classroomRepository.findOne(classroomId);
        if (classroom == null) {
            throw new NotFoundException("Classroom Not Found!");
        }
        List<FreeOption> freeOptions = new ArrayList<>();

        List<FreeOptionCell> freeOptionCells = freeOptionCellRepository.findAllByClassroom(classroom);

        Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();

        //verific ziua
        //verific ora
        //pentru aceasta zi, afisez doar disponibilitatile de dupa ora curenta
        WeekType currentWeekType = calculateWeekType();
        List<WeekType> weekTypes = new ArrayList<>();
        weekTypes.add(currentWeekType);
        if (!WeekType.EVEN_WEEK.equals(currentWeekType)) {
            weekTypes.add(WeekType.EVEN_WEEK);
        } else {
            weekTypes.add(WeekType.ODD_WEEK);
        }
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        String currentDay = initializeCurrentDay(calendar);


        for (WeekType weekType : weekTypes) {
            for (FreeOptionCell freeOptionCell : freeOptionCells) {
                if (freeOptionCell.getWeekType().equals(weekType)) {
                    if (currentDay.equals(freeOptionCell.getDay().name()) && (currentHour >= Integer.valueOf(freeOptionCell.getHour().substring(0, 2)))) {
                        continue;
                    }
                    FreeOption freeOption = new FreeOption();
                    freeOption.setClassroom(new ClassroomResponse(freeOptionCell.getClassroom()));
                    freeOption.setWeekType(freeOptionCell.getWeekType());
                    //TODO set DATE
                    freeOption.setDate(calendar.getTime());
                    calendar.add(Calendar.DATE, 1);

                    freeOption.setDay(freeOptionCell.getDay());
                    freeOption.setHour(freeOptionCell.getHour());
//            freeOption.setStudentsGroup(new StudentsGroupResponse(freeOptionCell.getStudentsGroup()));
//            freeOption.setSubgroup(freeOptionCell.getSubgroup());
                    freeOptions.add(freeOption);
                }
            }
        }


        return freeOptions;
    }

    public WeekType calculateWeekType() {
        Calendar calendar = Calendar.getInstance();
        calendar.getTime();
        Schedule schedule;
        if (calendar.get(Calendar.MONTH) < 2) {
            schedule = scheduleRepository.findByFaculty_IdAndSemester(1L, Semester.ONE);
        } else {
            schedule = scheduleRepository.findByFaculty_IdAndSemester(1L, Semester.TWO);
        }
        Date semesterStartDate = schedule.getSemesterStartDate();
        WeekType currentWeekType;
        int weeks = Weeks.weeksBetween(new DateTime(semesterStartDate), new DateTime(calendar.getTime())).getWeeks();
        if (weeks % 2 == 0) {
            currentWeekType = WeekType.EVEN_WEEK;
        } else {
            currentWeekType = WeekType.ODD_WEEK;
        }
        return currentWeekType;
    }

    private String initializeCurrentDay(Calendar calendar) {
        String currentDay = "";
        int currentIntDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (currentIntDay == 1) {
            currentDay = Day.MONDAY.name();
        } else if (currentIntDay == 2) {
            currentDay = Day.TUESDAY.name();
        } else if (currentIntDay == 3) {
            currentDay = Day.WEDNESDAY.name();
        } else if (currentIntDay == 4) {
            currentDay = Day.THURSDAY.name();
        } else if (currentIntDay == 5) {
            currentDay = Day.FRIDAY.name();
        } else if (currentIntDay == 6) {
            currentDay = Day.SATURDAY.name();
        } else if (currentIntDay == 7) {
            currentDay = Day.SUNDAY.name();
        }

        return currentDay;
    }
}
