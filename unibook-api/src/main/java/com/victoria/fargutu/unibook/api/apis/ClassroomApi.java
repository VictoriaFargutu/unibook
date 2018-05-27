package com.victoria.fargutu.unibook.api.apis;

import com.victoria.fargutu.unibook.repository.commons.ClassroomType;
import com.victoria.fargutu.unibook.repository.commons.UserRole;
import com.victoria.fargutu.unibook.repository.model.classroom.ClassroomResponse;
import com.victoria.fargutu.unibook.service.classroom.ClassroomService;
import com.victoria.fargutu.unibook.service.security.HasRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/classrooms")
public class ClassroomApi {
    private final ClassroomService classroomService;

    public ClassroomApi(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @HasRole(UserRole.USER)
    @GetMapping(value = "/course")
    public List<ClassroomResponse> getAllCourseClassrooms() {
        return classroomService.getAllClassroomsByType(ClassroomType.COURSE);
    }

    @HasRole(UserRole.USER)
    @GetMapping(value = "/lab")
    public List<ClassroomResponse> getAllLabClassrooms() {
        return classroomService.getAllClassroomsByType(ClassroomType.LAB);
    }

    @HasRole(UserRole.USER)
    @GetMapping(value = "/seminar")
    public List<ClassroomResponse> getAllSeminarClassrooms() {
        return classroomService.getAllClassroomsByType(ClassroomType.SEMINAR);
    }

}
