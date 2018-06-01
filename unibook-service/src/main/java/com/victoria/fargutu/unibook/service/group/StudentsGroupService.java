package com.victoria.fargutu.unibook.service.group;

import com.victoria.fargutu.unibook.repository.model.studentsGroup.StudentsGroup;
import com.victoria.fargutu.unibook.repository.model.studentsGroup.StudentsGroupResponse;

import java.util.List;

public interface StudentsGroupService {
    List<StudentsGroupResponse> getAllStudentsGroups();
    List<StudentsGroup> getStudentsGroups();
}
