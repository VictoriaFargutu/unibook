package com.victoria.fargutu.unibook.service.free_option;

import com.victoria.fargutu.unibook.repository.db.ClassroomRepository;
import com.victoria.fargutu.unibook.repository.db.ScheduleCellRepository;
import com.victoria.fargutu.unibook.repository.model.classroom.Classroom;
import com.victoria.fargutu.unibook.repository.model.classroom.ClassroomResponse;
import com.victoria.fargutu.unibook.repository.model.free_option.FreeOption;
import com.victoria.fargutu.unibook.repository.model.schedulleCell.ScheduleCell;
import com.victoria.fargutu.unibook.repository.model.studentsGroup.StudentsGroupResponse;
import com.victoria.fargutu.unibook.service.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FreeOptionServiceImpl implements FreeOptionService {
    private ScheduleCellRepository scheduleCellRepository;
    private ClassroomRepository classroomRepository;

    public FreeOptionServiceImpl(ScheduleCellRepository scheduleCellRepository, ClassroomRepository classroomRepository) {
        this.scheduleCellRepository = scheduleCellRepository;
        this.classroomRepository = classroomRepository;
    }

    @Override
    public List<FreeOption> getAllFreeOptionsByClassroom(Long classroomId) {
        Classroom classroom = classroomRepository.findOne(classroomId);
        if (classroom == null) {
            throw new NotFoundException("Classroom Not Found!");
        }
        List<FreeOption> freeOptions = new ArrayList<>();

        List<ScheduleCell> scheduleCells = scheduleCellRepository.findAllByClassroom(classroom);
//        for (ScheduleCell scheduleCell : scheduleCells) {
//            FreeOption freeOption = new FreeOption();
//            freeOption.setClassroom(new ClassroomResponse(scheduleCell.getClassroom()));
//            freeOption.setWeekType(scheduleCell.getWeekType());
//            //TODO set DATE
//            freeOption.setDay(scheduleCell.getDay());
//            freeOption.setHour(scheduleCell.getHour());
//            freeOption.setStudentsGroup(new StudentsGroupResponse(scheduleCell.getStudentsGroup()));
//            freeOption.setSubgroup(scheduleCell.getSubgroup());
//
//            freeOptions.add(freeOption);
//        }


        return freeOptions;
    }
}
