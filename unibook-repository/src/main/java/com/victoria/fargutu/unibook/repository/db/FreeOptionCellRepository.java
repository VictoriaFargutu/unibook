package com.victoria.fargutu.unibook.repository.db;

import com.victoria.fargutu.unibook.repository.commons.Day;
import com.victoria.fargutu.unibook.repository.commons.WeekType;
import com.victoria.fargutu.unibook.repository.model.classroom.Classroom;
import com.victoria.fargutu.unibook.repository.model.free_option_cell.FreeOptionCell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreeOptionCellRepository extends JpaRepository<FreeOptionCell, Long> {
    List<FreeOptionCell> findAllByClassroom(Classroom classroom);

    List<FreeOptionCell> findAllByDay(Day day);

    List<FreeOptionCell> findAllByHour(String hour);

    List<FreeOptionCell> findAllByWeekType(WeekType weekType);

}
