package com.victoria.fargutu.unibook.repository.db;

import com.victoria.fargutu.unibook.repository.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
