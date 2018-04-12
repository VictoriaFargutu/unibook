package com.victoria.fargutu.unibook.service.reservation;

import com.victoria.fargutu.unibook.repository.db.ReservationRepository;
import com.victoria.fargutu.unibook.repository.model.reservation.Reservation;
import com.victoria.fargutu.unibook.repository.model.reservation.ReservationResponse;
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
    public ReservationResponse createReservation(Reservation reservation) {
        //TODO if is possible then make reservation
        return new ReservationResponse(reservationRepository.save(reservation));
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findOne(id);
    }

    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {

        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
