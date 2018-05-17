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

import java.util.*;

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
        Day currentDay = initializeCurrentDay(calendar);
        Map<Day, Integer> days = new HashMap<>();

        days.put(Day.MONDAY, 1);
        days.put(Day.TUESDAY, 2);
        days.put(Day.WEDNESDAY, 3);
        days.put(Day.THURSDAY, 4);
        days.put(Day.FRIDAY, 5);
        days.put(Day.SATURDAY, 6);
        days.put(Day.SUNDAY, 7);

//        Day currentDay = days.get(calendar.get(Calendar.DAY_OF_WEEK));
        Day freeOptionCellDay;
        Day tempDay = freeOptionCells.get(0).getDay();
        int count = 0;
        for (WeekType weekType : weekTypes) {
            for (FreeOptionCell freeOptionCell : freeOptionCells) {

                freeOptionCellDay = freeOptionCell.getDay();

                if (freeOptionCell.getWeekType().equals(weekType)) {
                    if (freeOptionCell.getWeekType().equals(weekTypes.get(0)) && days.get(currentDay) > days.get(freeOptionCellDay)) {
                        continue;
                    }

                    if (currentDay.name().equals(freeOptionCell.getDay().name()) && (currentHour >= Integer.valueOf(freeOptionCell.getHour().substring(0, 2)))) {
                        continue;
                    }
                    //TODO define a dictionary for days!!!
                    //incepe de la ziua curenta
                    //schimba data cand se schimba ziua
                    count++;
                    FreeOption freeOption = new FreeOption();
                    freeOption.setClassroom(new ClassroomResponse(freeOptionCell.getClassroom()));
                    freeOption.setWeekType(freeOptionCell.getWeekType());
                    //TODO set DATE
                    if (count > 1 && !tempDay.equals(freeOptionCellDay)) {
                        calendar.add(Calendar.DATE, 1);
                        freeOption.setDate(calendar.getTime());
                        tempDay = freeOptionCellDay;
                    }
                    freeOption.setDate(calendar.getTime());
                    if (freeOptionCellDay.equals(currentDay)) {
                        tempDay = freeOptionCellDay;
                    }
                    freeOption.setDay(freeOptionCell.getDay());
                    freeOption.setHour(freeOptionCell.getHour());

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

    private Day initializeCurrentDay(Calendar calendar) {
        Day currentDay = null;
        int currentIntDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (currentIntDay == 1) {
            currentDay = Day.SUNDAY;
        } else if (currentIntDay == 2) {
            currentDay = Day.MONDAY;
        } else if (currentIntDay == 3) {
            currentDay = Day.TUESDAY;
        } else if (currentIntDay == 4) {
            currentDay = Day.WEDNESDAY;
        } else if (currentIntDay == 5) {
            currentDay = Day.THURSDAY;
        } else if (currentIntDay == 6) {
            currentDay = Day.FRIDAY;
        } else if (currentIntDay == 7) {
            currentDay = Day.SATURDAY;
        }

        return currentDay;

    }
}
