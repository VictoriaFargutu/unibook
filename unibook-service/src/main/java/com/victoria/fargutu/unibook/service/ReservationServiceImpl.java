package com.victoria.fargutu.unibook.service;

import com.victoria.fargutu.unibook.repository.db.ReservationRepository;
import com.victoria.fargutu.unibook.repository.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        return null;
    }

    @Override
    public Reservation getReservationById(Long id) {
        return null;
    }

    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
