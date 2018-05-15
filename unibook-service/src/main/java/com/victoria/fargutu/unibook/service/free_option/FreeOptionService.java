package com.victoria.fargutu.unibook.service.free_option;

import com.victoria.fargutu.unibook.repository.model.classroom.Classroom;
import com.victoria.fargutu.unibook.repository.model.free_option.FreeOption;

import java.util.List;

public interface FreeOptionService {
    List<FreeOption> getAllFreeOptionsByClassroom(Classroom classroom);
}
