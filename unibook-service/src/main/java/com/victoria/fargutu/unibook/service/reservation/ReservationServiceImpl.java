package com.victoria.fargutu.unibook.service.reservation;

import com.victoria.fargutu.unibook.repository.db.ReservationRepository;
import com.victoria.fargutu.unibook.repository.model.AuthManager;
import com.victoria.fargutu.unibook.repository.model.reservation.Reservation;
import com.victoria.fargutu.unibook.repository.model.reservation.ReservationResponse;
import com.victoria.fargutu.unibook.service.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;
    private AuthManager authManager;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, AuthManager authManager) {
        this.reservationRepository = reservationRepository;
        this.authManager = authManager;
    }

    @Override
    public ReservationResponse createReservation(Reservation reservation) {
        //TODO if is possible then make reservation
        reservation.setUser(authManager.getLoggedInUser());
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
    public void deleteReservationById(Long id) {
        Reservation reservation = reservationRepository.findOne(id);
        if (reservation == null) {
            throw new NotFoundException("Reservation not found");
        }
        reservationRepository.delete(id);
    }

    @Override
    public List<ReservationResponse> getReservationsByUserId(Long id) {
        List<Reservation> reservations = reservationRepository.findAllByUserId(id);
        List<ReservationResponse> reservationResponses = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationResponses.add(new ReservationResponse(reservation));
        }
        return reservationResponses;
    }
}
