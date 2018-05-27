package com.victoria.fargutu.unibook.api.apis;

import com.victoria.fargutu.unibook.repository.commons.UserRole;
import com.victoria.fargutu.unibook.repository.model.AuthManager;
import com.victoria.fargutu.unibook.repository.model.reservation.Reservation;
import com.victoria.fargutu.unibook.repository.model.reservation.ReservationResponse;
import com.victoria.fargutu.unibook.service.reservation.ReservationService;
import com.victoria.fargutu.unibook.service.security.HasRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reservations")
public class ReservationApi {
    private ReservationService reservationService;
    private AuthManager authManager;

    @Autowired
    public ReservationApi(ReservationService reservationService, AuthManager authManager) {
        this.reservationService = reservationService;
        this.authManager = authManager;
    }

    @HasRole(UserRole.USER)
    @PostMapping
    public ReservationResponse createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @HasRole(UserRole.USER)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void cancelReservation(@PathVariable Long id) {
        reservationService.deleteReservationById(id);
    }

    @HasRole(UserRole.USER)
    @GetMapping
    public List<ReservationResponse> getReservationsByUserId(@RequestParam(value = "id") Long id) {
        return reservationService.getReservationsByUserId(id);
    }
}
