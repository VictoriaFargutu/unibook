package com.victoria.fargutu.unibook.repository.db;

import com.victoria.fargutu.unibook.repository.commons.Semester;
import com.victoria.fargutu.unibook.repository.model.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Schedule findByFaculty_IdAndSemester(Long id, Semester semester);
}
