package com.victoria.fargutu.unibook.repository.db;

import com.victoria.fargutu.unibook.repository.model.faculty.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
