package com.victoria.fargutu.unibook.repository.db;

import com.victoria.fargutu.unibook.repository.model.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
