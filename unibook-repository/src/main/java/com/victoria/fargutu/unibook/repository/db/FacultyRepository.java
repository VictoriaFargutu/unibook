package com.victoria.fargutu.unibook.repository.db;

import com.victoria.fargutu.unibook.repository.model.Faculty.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
