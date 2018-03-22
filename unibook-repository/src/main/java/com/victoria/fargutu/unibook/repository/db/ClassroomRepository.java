package com.victoria.fargutu.unibook.repository.db;

import com.victoria.fargutu.unibook.repository.model.classroom.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
