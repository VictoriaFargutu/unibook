package com.victoria.fargutu.unibook.service.classroom;

import com.victoria.fargutu.unibook.repository.commons.ClassroomType;
import com.victoria.fargutu.unibook.repository.model.classroom.Classroom;
import com.victoria.fargutu.unibook.repository.model.classroom.ClassroomResponse;

import java.util.List;

public interface ClassroomService {
    List<ClassroomResponse> getAllClassroomsByType(ClassroomType classroomType);

    List<Classroom> getAllClassrooms();
}
