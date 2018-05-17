package com.victoria.fargutu.unibook.repository.db;

import com.victoria.fargutu.unibook.repository.model.classroom.Classroom;
import com.victoria.fargutu.unibook.repository.model.free_option_cell.FreeOptionCell;
import com.victoria.fargutu.unibook.repository.model.schedulleCell.ScheduleCell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreeOptionCellRepository extends JpaRepository<FreeOptionCell, Long> {
    List<FreeOptionCell> findAllByClassroom(Classroom classroom);
}
