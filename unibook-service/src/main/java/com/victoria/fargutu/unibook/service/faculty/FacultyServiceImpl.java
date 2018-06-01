package com.victoria.fargutu.unibook.service.faculty;

import com.victoria.fargutu.unibook.repository.db.FacultyRepository;
import com.victoria.fargutu.unibook.repository.model.faculty.Faculty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {
    private FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }
}
