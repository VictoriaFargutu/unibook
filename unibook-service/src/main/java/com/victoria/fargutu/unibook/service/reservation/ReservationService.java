package com.victoria.fargutu.unibook.service.reservation;

import com.victoria.fargutu.unibook.repository.model.reservation.Reservation;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);

    Reservation getReservationById(Long id);

    Reservation updateReservation(Long id, Reservation reservation);

    void deleteById(Long id);
}
