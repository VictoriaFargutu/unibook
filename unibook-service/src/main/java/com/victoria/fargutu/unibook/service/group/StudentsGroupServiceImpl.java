package com.victoria.fargutu.unibook.service.group;

import com.victoria.fargutu.unibook.repository.db.StudentsGroupRepository;
import com.victoria.fargutu.unibook.repository.model.studentsGroup.StudentsGroup;
import com.victoria.fargutu.unibook.repository.model.studentsGroup.StudentsGroupResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentsGroupServiceImpl implements StudentsGroupService {
    private StudentsGroupRepository studentsGroupRepository;

    public StudentsGroupServiceImpl(StudentsGroupRepository studentsGroupRepository) {
        this.studentsGroupRepository = studentsGroupRepository;
    }

    @Override
    public List<StudentsGroupResponse> getAllStudentsGroups() {
        List<StudentsGroup> studentsGroups = studentsGroupRepository.findAll();
        List<StudentsGroupResponse> studentsGroupResponses = new ArrayList<>();
        if (!studentsGroups.isEmpty()) {
            for (StudentsGroup studentsGroup : studentsGroups) {
                studentsGroupResponses.add(new StudentsGroupResponse(studentsGroup));
            }
        }

        return studentsGroupResponses;
    }
}
