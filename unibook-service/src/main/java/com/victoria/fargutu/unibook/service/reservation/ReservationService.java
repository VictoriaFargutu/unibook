package com.victoria.fargutu.unibook.service.reservation;

import com.victoria.fargutu.unibook.repository.model.reservation.Reservation;
import com.victoria.fargutu.unibook.repository.model.reservation.ReservationResponse;

import java.util.List;

public interface ReservationService {
    ReservationResponse createReservation(Reservation reservation);

    Reservation getReservationById(Long id);

    Reservation updateReservation(Long id, Reservation reservation);

    void deleteReservationById(Long id);

    List<ReservationResponse> getReservationsByUserId(Long id);
}
