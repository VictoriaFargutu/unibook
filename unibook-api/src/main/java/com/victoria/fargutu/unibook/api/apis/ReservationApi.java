package com.victoria.fargutu.unibook.api.apis;

import com.victoria.fargutu.unibook.repository.model.reservation.Reservation;
import com.victoria.fargutu.unibook.repository.model.reservation.ReservationResponse;
import com.victoria.fargutu.unibook.service.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void cancelReservation(@PathVariable Long id) {
        reservationService.deleteReservationById(id);
    }

    @GetMapping
    public List<ReservationResponse> getReservationsByUserId(@RequestParam(value = "id") Long id) {
        return reservationService.getReservationsByUserId(id);
    }
}
