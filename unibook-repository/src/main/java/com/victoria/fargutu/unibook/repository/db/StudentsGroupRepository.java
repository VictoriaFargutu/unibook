package com.victoria.fargutu.unibook.repository.db;

import com.victoria.fargutu.unibook.repository.model.studentsGroup.StudentsGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsGroupRepository extends JpaRepository<StudentsGroup, Long> {
}
