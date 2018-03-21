package com.victoria.fargutu.unibook.repository.db;

import com.victoria.fargutu.unibook.repository.model.schedule.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
