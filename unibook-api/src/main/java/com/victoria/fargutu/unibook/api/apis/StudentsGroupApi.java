package com.victoria.fargutu.unibook.api.apis;

import com.victoria.fargutu.unibook.repository.model.studentsGroup.StudentsGroupResponse;
import com.victoria.fargutu.unibook.service.group.StudentsGroupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/group")
public class StudentsGroupApi {
    private StudentsGroupService studentsGroupService;

    public StudentsGroupApi(StudentsGroupService studentsGroupService) {
        this.studentsGroupService = studentsGroupService;
    }

    @GetMapping
    public List<StudentsGroupResponse> getAllStudentsGroups() {
        return studentsGroupService.getAllStudentsGroups();
    }
}
