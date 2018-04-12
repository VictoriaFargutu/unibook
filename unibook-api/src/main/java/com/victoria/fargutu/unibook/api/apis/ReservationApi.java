package com.victoria.fargutu.unibook.api.apis;

import com.victoria.fargutu.unibook.repository.model.reservation.Reservation;
import com.victoria.fargutu.unibook.repository.model.reservation.ReservationResponse;
import com.victoria.fargutu.unibook.service.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/reservations")
public class ReservationApi {
    private ReservationService reservationService;

    @Autowired
    public ReservationApi(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ReservationResponse createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }
}
