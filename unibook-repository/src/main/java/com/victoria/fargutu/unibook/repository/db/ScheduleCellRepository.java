package com.victoria.fargutu.unibook.repository.db;

import com.victoria.fargutu.unibook.repository.model.schedule.ScheduleCell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleCellRepository extends JpaRepository<ScheduleCell, Long> {
}
