package com.victoria.fargutu.unibook.service.free_option;

import com.victoria.fargutu.unibook.repository.db.ScheduleCellRepository;
import com.victoria.fargutu.unibook.repository.model.classroom.Classroom;
import com.victoria.fargutu.unibook.repository.model.free_option.FreeOption;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FreeOptionServiceImpl implements FreeOptionService {
    private ScheduleCellRepository scheduleCellRepository;

    public FreeOptionServiceImpl(ScheduleCellRepository scheduleCellRepository) {
        this.scheduleCellRepository = scheduleCellRepository;
    }

    @Override
    public List<FreeOption> getAllFreeOptionsByClassroom(Classroom classroom) {
        List<FreeOption> freeOptions = new ArrayList<>();
        scheduleCellRepository.findAllByClassroom(classroom);
        FreeOption freeOption = new FreeOption();
        freeOption.setClassroom(classroom);
        freeOptions.add(freeOption);
        return freeOptions;
    }
}
