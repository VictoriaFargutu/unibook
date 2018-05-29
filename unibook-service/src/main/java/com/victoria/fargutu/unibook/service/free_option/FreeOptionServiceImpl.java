package com.victoria.fargutu.unibook.service.free_option;

import com.victoria.fargutu.unibook.repository.commons.Day;
import com.victoria.fargutu.unibook.repository.commons.Semester;
import com.victoria.fargutu.unibook.repository.commons.WeekType;
import com.victoria.fargutu.unibook.repository.db.ClassroomRepository;
import com.victoria.fargutu.unibook.repository.db.FreeOptionCellRepository;
import com.victoria.fargutu.unibook.repository.db.ScheduleCellRepository;
import com.victoria.fargutu.unibook.repository.db.ScheduleRepository;
import com.victoria.fargutu.unibook.repository.model.Filter;
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
    public List<FreeOption> getAllFreeOptions() {
        List<FreeOptionCell> freeOptionCells = freeOptionCellRepository.findAll();
        return getFreeOptions(freeOptionCells);
    }

    @Override
    public List<FreeOption> getAllFreeOptionsByClassroom(Long classroomId) {
        Classroom classroom = classroomRepository.findOne(classroomId);
        if (classroom == null) {
            throw new NotFoundException("Classroom Not Found!");
        }

        List<FreeOptionCell> freeOptionCells = freeOptionCellRepository.findAllByClassroom(classroom);

        return getFreeOptions(freeOptionCells);
    }

//    @Override
//    public List<FreeOption> getAllFreeOptionsByFilter(Filter filter) {
//        List<FreeOptionCell> freeOptionCells = new ArrayList<>();
//        List<FreeOption> freeOptions = new ArrayList<>();
//
//        Classroom classroom = filter.getClassroom();
//        Day day = filter.getDay();
//        String hour = filter.getHour();
//        WeekType weekType = filter.getWeekType();
//
//        if (classroom != null) {
//            classroom = classroomRepository.findOne(classroom.getId());
//            if (classroom == null) {
//                throw new NotFoundException("Classroom Not Found!");
//            }
//            freeOptionCells = freeOptionCellRepository.findAllByClassroom(classroom);
//        }
//        if (day != null) {
//            freeOptionCells.addAll(freeOptionCellRepository.findAllByDay(day));
//
//            for (FreeOptionCell freeOptionCell : freeOptionCells) {
//                if (!freeOptionCell.getDay().equals(day)) {
//                    freeOptionCells.remove(freeOptionCell);
//                }
//            }
//        }
//        if (hour != null) {
//            freeOptionCells.addAll(freeOptionCellRepository.findAllByHour(hour));
//
//            for (FreeOptionCell freeOptionCell : freeOptionCells) {
//                if (!freeOptionCell.getHour().equals(hour)) {
//                    freeOptionCells.remove(freeOptionCell);
//                }
//            }
//        }
//        if (weekType != null) {
//            freeOptionCells.addAll(freeOptionCellRepository.findAllByWeekType(weekType));
//
//            for (FreeOptionCell freeOptionCell : freeOptionCells) {
//                if (!freeOptionCell.getWeekType().equals(weekType)) {
//                    freeOptionCells.remove(freeOptionCell);
//                }
//            }
//        }
//        if (freeOptionCells.size() == 0) {
//            freeOptionCells = freeOptionCellRepository.findAll();
//            freeOptions = getFreeOptions(freeOptionCells);
//        }
//
//        if (classroom == null) {
//            for (FreeOptionCell freeOptionCell : freeOptionCells) {
//
//            }
//        }
//
//        return freeOptions;
//    }

    @Override
    public List<FreeOption> getAllFreeOptionsByFilter(Filter filter) {
        List<FreeOptionCell> freeOptionCells = new ArrayList<>();
        List<FreeOption> freeOptions = new ArrayList<>();

        Classroom classroom = filter.getClassroom();
        Day day = filter.getDay();
        String hour = filter.getHour();
        WeekType weekType = filter.getWeekType();

        if (classroom != null) {
            classroom = classroomRepository.findOne(classroom.getId());
            if (classroom == null) {
                throw new NotFoundException("Classroom Not Found!");
            }
            freeOptionCells = freeOptionCellRepository.findAllByClassroom(classroom);
        }

        if (day != null && !freeOptionCells.isEmpty()) {
            List<FreeOptionCell> freeOptionCellsDays = new ArrayList<>();
            for (FreeOptionCell freeOptionCell : freeOptionCells) {
                if (freeOptionCell.getDay().equals(day)) {
                    freeOptionCellsDays.add(freeOptionCell);
//                    freeOptionCells.remove(freeOptionCell);
                }
            }
            freeOptionCells = freeOptionCellsDays;
        } else if (freeOptionCells.isEmpty()) {
            freeOptionCells.addAll(freeOptionCellRepository.findAllByDay(day));
        }

        if (hour != null && !freeOptionCells.isEmpty()) {
            List<FreeOptionCell> freeOptionCellsHour = new ArrayList<>();
            for (FreeOptionCell freeOptionCell : freeOptionCells) {
                if (freeOptionCell.getHour().equals(hour)) {
                    freeOptionCellsHour.add(freeOptionCell);
//                    freeOptionCells.remove(freeOptionCell);
                }
            }
            freeOptionCells = freeOptionCellsHour;
        } else if (freeOptionCells.isEmpty()) {
            freeOptionCells.addAll(freeOptionCellRepository.findAllByHour(hour));
        }

        if (weekType != null && !freeOptionCells.isEmpty()) {
            List<FreeOptionCell> freeOptionCellsWeek = new ArrayList<>();
            for (FreeOptionCell freeOptionCell : freeOptionCells) {
                if (freeOptionCell.getWeekType().equals(weekType)) {
                    freeOptionCellsWeek.add(freeOptionCell);
                }
            }
            freeOptionCells = freeOptionCellsWeek;
        } else if (freeOptionCells.isEmpty()) {
            freeOptionCells.addAll(freeOptionCellRepository.findAllByWeekType(weekType));
        }

        if (freeOptionCells.size() == 0) {
            freeOptionCells = freeOptionCellRepository.findAll();
        }
        freeOptions = getFreeOptions(freeOptionCells);

        //TODO  Look in schedule cells

        if (classroom == null) {
            for (FreeOptionCell freeOptionCell : freeOptionCells) {

            }
        }
        return freeOptions;
    }

    public List<FreeOption> getFreeOptions(List<FreeOptionCell> freeOptionCells) {
        List<FreeOption> freeOptions = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

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
                    if (currentDay.name().equals(freeOptionCell.getDay().name()) && currentHour > 22 && currentHour < 24 && freeOptionCell.getHour().substring(0, 2).equals("08")) {
                        calendar.add(Calendar.DATE, 1);
                    }
                    count++;
                    FreeOption freeOption = new FreeOption();
                    freeOption.setClassroom(new ClassroomResponse(freeOptionCell.getClassroom()));
                    freeOption.setWeekType(freeOptionCell.getWeekType());

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

    private void getFreeOptionsByFilterTest(Filter filter) {
//        List<FreeOptionCell> freeOptionCells = new ArrayList<>();
//        List<FreeOption> freeOptions = new ArrayList<>();
//
//        Classroom classroom = filter.getClassroom();
//        Day day = filter.getDay();
//        String hour = filter.getHour();
//        WeekType weekType = filter.getWeekType();
//
//        if (classroom != null) {
//            classroom = classroomRepository.findOne(classroom.getId());
//            if (classroom == null) {
//                throw new NotFoundException("Classroom Not Found!");
//            }
//            freeOptionCells = freeOptionCellRepository.findAllByClassroom(classroom);
//        }
//
//        if (day != null && !freeOptionCells.isEmpty()) {
//            for (FreeOptionCell freeOptionCell : freeOptionCells) {
//                if (!freeOptionCell.getDay().equals(day)) {
//                    freeOptionCells.remove(freeOptionCell);
//                }
//            }
//        } else if (freeOptionCells.isEmpty()) {
//            freeOptionCells.addAll(freeOptionCellRepository.findAllByDay(day));
//        }
//
//        if (hour != null && !freeOptionCells.isEmpty()) {
//            for (FreeOptionCell freeOptionCell : freeOptionCells) {
//                if (!freeOptionCell.getHour().equals(hour)) {
//                    freeOptionCells.remove(freeOptionCell);
//                }
//            }
//        } else if (freeOptionCells.isEmpty()) {
//            freeOptionCells.addAll(freeOptionCellRepository.findAllByHour(hour));
//        }
//
//        if (weekType != null && !freeOptionCells.isEmpty()) {
//            for (FreeOptionCell freeOptionCell : freeOptionCells) {
//                if (!freeOptionCell.getWeekType().equals(weekType)) {
//                    freeOptionCells.remove(freeOptionCell);
//                }
//            }
//        } else if (freeOptionCells.isEmpty()) {
//            freeOptionCells.addAll(freeOptionCellRepository.findAllByWeekType(weekType));
//        }
//
//        if (freeOptionCells.size() == 0) {
//            freeOptionCells = freeOptionCellRepository.findAll();
//        }
//        freeOptions = getFreeOptions(freeOptionCells);
//
//        //TODO  Look in schedule cells
//
//        if (classroom == null) {
//            for (FreeOptionCell freeOptionCell : freeOptionCells) {
//
//            }
//        }
    }

}
