package com.victoria.fargutu.unibook.service.classroom;

import com.victoria.fargutu.unibook.repository.commons.ClassroomType;
import com.victoria.fargutu.unibook.repository.db.ClassroomRepository;
import com.victoria.fargutu.unibook.repository.model.classroom.Classroom;
import com.victoria.fargutu.unibook.repository.model.classroom.ClassroomResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    private ClassroomRepository classroomRepository;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public List<ClassroomResponse> getAllClassroomsByType(ClassroomType classroomType) {
        List<Classroom> classrooms = classroomRepository.findAllByType(classroomType);

        List<ClassroomResponse> classroomResponses = new ArrayList<>();
        for (Classroom classroom : classrooms) {
            classroomResponses.add(new ClassroomResponse(classroom));
        }
        return classroomResponses;
    }
}
