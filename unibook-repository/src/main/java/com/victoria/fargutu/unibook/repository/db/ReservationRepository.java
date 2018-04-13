package com.victoria.fargutu.unibook.repository.db;

import com.victoria.fargutu.unibook.repository.model.reservation.Reservation;
import com.victoria.fargutu.unibook.repository.model.reservation.ReservationResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUserId(Long id);
}
