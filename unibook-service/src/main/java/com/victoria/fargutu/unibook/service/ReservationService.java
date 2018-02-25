package com.victoria.fargutu.unibook.service;

import com.victoria.fargutu.unibook.repository.model.Reservation;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);

    Reservation getReservationById(Long id);

    Reservation updateReservation(Long id, Reservation reservation);

    void deleteById(Long id);
}
