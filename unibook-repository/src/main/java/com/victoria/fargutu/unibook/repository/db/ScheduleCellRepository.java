package com.victoria.fargutu.unibook.repository.db;

import com.victoria.fargutu.unibook.repository.model.classroom.Classroom;
import com.victoria.fargutu.unibook.repository.model.scheduleCell.ScheduleCell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleCellRepository extends JpaRepository<ScheduleCell, Long> {
    List<ScheduleCell> findAllByClassroom(Classroom classroom);
}
