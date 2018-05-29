package com.victoria.fargutu.unibook.service.free_option;

import com.victoria.fargutu.unibook.repository.commons.*;
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
import com.victoria.fargutu.unibook.repository.model.schedulleCell.ScheduleCell;
import com.victoria.fargutu.unibook.repository.model.studentsGroup.StudentsGroup;
import com.victoria.fargutu.unibook.repository.model.studentsGroup.StudentsGroupResponse;
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

    @SuppressWarnings("Duplicates")
    @Override
    public List<FreeOption> getAllFreeOptionsByFilter(Filter filter) {
        List<FreeOptionCell> freeOptionCells = new ArrayList<>();
        List<FreeOption> freeOptions = new ArrayList<>();

        Classroom classroom = filter.getClassroom();
        ClassroomType classroomType = filter.getClassroomType();
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

        if (classroomType != null && !freeOptionCells.isEmpty()) {
            List<FreeOptionCell> freeOptionCellsClassType = new ArrayList<>();
            for (FreeOptionCell freeOptionCell : freeOptionCells) {
                if (freeOptionCell.getClassroom().getType().equals(classroomType)) {
                    freeOptionCellsClassType.add(freeOptionCell);
                }
            }
            freeOptionCells = freeOptionCellsClassType;
        } else if (freeOptionCells.isEmpty()) {
            freeOptionCells = freeOptionCellRepository.findAllByClassroomType(filter.getClassroomType());
        }

        if (day != null && !freeOptionCells.isEmpty()) {
            List<FreeOptionCell> freeOptionCellsDays = new ArrayList<>();
            for (FreeOptionCell freeOptionCell : freeOptionCells) {
                if (freeOptionCell.getDay().equals(day)) {
                    freeOptionCellsDays.add(freeOptionCell);
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
                }
            }
            freeOptionCells = freeOptionCellsHour;
        } else if (freeOptionCells.isEmpty()) {
            freeOptionCells.addAll(freeOptionCellRepository.findAllByHour(hour));
        }

        if (filter.getDate() == null && weekType != null && !freeOptionCells.isEmpty()) {
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

        //INITIAL FILTER WITH DATE
        if (filter.getDate() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(filter.getDate());

            WeekType currentWeekType = calculateWeekType(calendar);
            Day dateDay = initializeCurrentDay(calendar);

            //DAY
            if (day == null && !freeOptionCells.isEmpty()) {
                List<FreeOptionCell> freeOptionCellsDays = new ArrayList<>();
                for (FreeOptionCell freeOptionCell : freeOptionCells) {
                    if (freeOptionCell.getDay().equals(dateDay)) {
                        freeOptionCellsDays.add(freeOptionCell);
                    }
                }
                freeOptionCells = freeOptionCellsDays;
            } else if (freeOptionCells.isEmpty()) {
                freeOptionCells.addAll(freeOptionCellRepository.findAllByDay(dateDay));
            }

            //WEEK TYPE
            if (!freeOptionCells.isEmpty()) {
                List<FreeOptionCell> freeOptionCellsWeek = new ArrayList<>();
                for (FreeOptionCell freeOptionCell : freeOptionCells) {
                    if (freeOptionCell.getWeekType().equals(currentWeekType)) {
                        freeOptionCellsWeek.add(freeOptionCell);
                    }
                }
                freeOptionCells = freeOptionCellsWeek;
            } else if (freeOptionCells.isEmpty()) {
                freeOptionCells.addAll(freeOptionCellRepository.findAllByWeekType(currentWeekType));
            }
            freeOptions = getFreeOptionsByDate(freeOptionCells, filter);
        } else{
            freeOptions = getFreeOptions(freeOptionCells);
        }

        if (freeOptionCells.size() == 0) {
            freeOptionCells = freeOptionCellRepository.findAll();
            freeOptions = getFreeOptions(freeOptionCells);
        }

        List<ScheduleCell> scheduleCells = scheduleCellRepository.findAll();
        StudentsGroup studentsGroup = filter.getStudentsGroup();
        Specialization specialization = filter.getSpecialization();
        String year = filter.getYear();

        if (year != null && studentsGroup == null && specialization == null) {
            List<FreeOption> freeOptionsByYear = new ArrayList<>();
            for (FreeOption freeOption : freeOptions) {
                for (ScheduleCell scheduleCell : scheduleCells) {
                    if (scheduleCell.getWeekType().equals(freeOption.getWeekType())) {
                        if (scheduleCell.getDay().equals(freeOption.getDay())) {
                            if (scheduleCell.getHour().equals(freeOption.getHour())) {
                                if (scheduleCell.getStudentsGroup().getYear().equals(year)) {
                                    if (scheduleCells.indexOf(scheduleCell) == scheduleCells.size() - 1) {
                                        break;
                                    } else {
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                    freeOptionsByYear.add(freeOption);
                }
            }
            freeOptions = freeOptionsByYear;
        }

        if (specialization != null && studentsGroup == null) {
            List<FreeOption> freeOptionsBySpecialization = new ArrayList<>();
            for (FreeOption freeOption : freeOptions) {
                for (ScheduleCell scheduleCell : scheduleCells) {
                    if (scheduleCell.getWeekType().equals(freeOption.getWeekType())) {
                        if (scheduleCell.getDay().equals(freeOption.getDay())) {
                            if (scheduleCell.getHour().equals(freeOption.getHour())) {
                                if (scheduleCell.getStudentsGroup().getSpecialization().equals(specialization)) {
                                    if (scheduleCells.indexOf(scheduleCell) == scheduleCells.size() - 1) {
                                        break;
                                    } else {
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                    freeOptionsBySpecialization.add(freeOption);
                }
            }
            freeOptions = freeOptionsBySpecialization;
        }

        if (studentsGroup != null) {
            List<FreeOption> freeOptionByGroup = new ArrayList<>();
            for (FreeOption freeOption : freeOptions) {
                for (ScheduleCell scheduleCell : scheduleCells) {
                    if (scheduleCell.getWeekType().equals(freeOption.getWeekType())) {
                        if (scheduleCell.getDay().equals(freeOption.getDay())) {
                            if (scheduleCell.getHour().equals(freeOption.getHour())) {
                                if (scheduleCell.getStudentsGroup().equals(studentsGroup)) {
                                    if (scheduleCells.indexOf(scheduleCell) == scheduleCells.size() - 1) {
                                        break;
                                    } else {
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                    if (filter.getSubgroup() != null && scheduleCell.getSubgroup() != null) {
                        if (scheduleCell.getWeekType().equals(freeOption.getWeekType())) {
                            if (scheduleCell.getDay().equals(freeOption.getDay())) {
                                if (scheduleCell.getHour().equals(freeOption.getHour())) {
                                    if (scheduleCell.getSubgroup().equals(filter.getSubgroup())) {
                                        if (scheduleCells.indexOf(scheduleCell) == scheduleCells.size() - 1) {
                                            break;
                                        } else {
                                            continue;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    freeOption.setStudentsGroup(new StudentsGroupResponse(studentsGroup));
                    if (filter.getSubgroup() != null) {
                        freeOption.setSubgroup(filter.getSubgroup());
                    }
                    freeOptionByGroup.add(freeOption);
                }

            }
            freeOptions = freeOptionByGroup;
        }

        return freeOptions;
    }

    @SuppressWarnings("Duplicates")
    public List<FreeOption> getFreeOptionsByDate(List<FreeOptionCell> freeOptionCells, Filter filter) {
        List<FreeOption> freeOptions = new ArrayList<>();
        List<ScheduleCell> scheduleCells = scheduleCellRepository.findAll();
        for (FreeOptionCell freeOptionCell : freeOptionCells) {
            FreeOption freeOption = new FreeOption();
            freeOption.setClassroom(new ClassroomResponse(freeOptionCell.getClassroom()));
            freeOption.setWeekType(freeOptionCell.getWeekType());
            freeOption.setDate(filter.getDate());
            freeOption.setDay(freeOptionCell.getDay());
            freeOption.setHour(freeOptionCell.getHour());

            StudentsGroup studentsGroup = filter.getStudentsGroup();
            Specialization specialization = filter.getSpecialization();
            String year = filter.getYear();

            if (year != null && studentsGroup == null && specialization == null) {
                for (ScheduleCell scheduleCell : scheduleCells) {
                    if (scheduleCell.getWeekType().equals(freeOptionCell.getWeekType())) {
                        if (scheduleCell.getDay().equals(freeOptionCell.getDay())) {
                            if (scheduleCell.getHour().equals(freeOptionCell.getHour())) {
                                if (scheduleCell.getStudentsGroup().getYear().equals(year)) {
                                    if (scheduleCells.indexOf(scheduleCell) == scheduleCells.size() - 1) {
                                        break;
                                    } else {
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                    freeOptions.add(freeOption);
                }
            } else {
                freeOptions.add(freeOption);
            }

            if (specialization != null && studentsGroup == null) {
                for (ScheduleCell scheduleCell : scheduleCells) {
                    if (scheduleCell.getWeekType().equals(freeOptionCell.getWeekType())) {
                        if (scheduleCell.getDay().equals(freeOptionCell.getDay())) {
                            if (scheduleCell.getHour().equals(freeOptionCell.getHour())) {
                                if (scheduleCell.getStudentsGroup().getSpecialization().equals(specialization)) {
                                    if (scheduleCells.indexOf(scheduleCell) == scheduleCells.size() - 1) {
                                        break;
                                    } else {
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                    freeOptions.add(freeOption);
                }
            } else {
                freeOptions.add(freeOption);
            }

            if (studentsGroup != null) {
                for (ScheduleCell scheduleCell : scheduleCells) {
                    if (scheduleCell.getWeekType().equals(freeOptionCell.getWeekType())) {
                        if (scheduleCell.getDay().equals(freeOptionCell.getDay())) {
                            if (scheduleCell.getHour().equals(freeOptionCell.getHour())) {
                                if (scheduleCell.getStudentsGroup().equals(studentsGroup)) {
                                    if (scheduleCells.indexOf(scheduleCell) == scheduleCells.size() - 1) {
                                        break;
                                    } else {
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                    if (filter.getSubgroup() != null && scheduleCell.getSubgroup() != null) {
                        if (scheduleCell.getWeekType().equals(freeOptionCell.getWeekType())) {
                            if (scheduleCell.getDay().equals(freeOptionCell.getDay())) {
                                if (scheduleCell.getHour().equals(freeOptionCell.getHour())) {
                                    if (scheduleCell.getSubgroup().equals(filter.getSubgroup())) {
                                        if (scheduleCells.indexOf(scheduleCell) == scheduleCells.size() - 1) {
                                            break;
                                        } else {
                                            continue;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    freeOption.setStudentsGroup(new StudentsGroupResponse(studentsGroup));
                    if (filter.getSubgroup() != null) {
                        freeOption.setSubgroup(filter.getSubgroup());
                    }
                    freeOptions.add(freeOption);
                }

            } else {
                freeOptions.add(freeOption);
            }
        }
        return freeOptions;
    }

    public List<FreeOption> getFreeOptions(List<FreeOptionCell> freeOptionCells) {
        List<FreeOption> freeOptions = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
//        calendar.getTime();

        WeekType currentWeekType = calculateWeekType(calendar);
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

    public WeekType calculateWeekType(Calendar calendar) {
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
