package com.victoria.fargutu.unibook.service.scheduler;

import com.victoria.fargutu.unibook.repository.db.ReservationRepository;
import com.victoria.fargutu.unibook.repository.model.reservation.Reservation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class DeleteExpiredReservationsJob {
    private ReservationRepository reservationRepository;

    public DeleteExpiredReservationsJob(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Scheduled(fixedRate = 1000 * 60 * 3600 * 2)
    public void deleteExpiredReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation : reservations) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(reservation.getDate());

            Calendar tempCalendar = Calendar.getInstance();
            tempCalendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
            tempCalendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
            tempCalendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND));
            tempCalendar.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND));

            if (tempCalendar.getTime().equals(calendar.getTime()) || tempCalendar.getTime().getTime() > reservation.getDate().getTime()) {
                tempCalendar = Calendar.getInstance();
                int currentHour = tempCalendar.get(Calendar.HOUR_OF_DAY);
                if ((currentHour >= Integer.valueOf(reservation.getHour().substring(0, 2)))) {
                    reservationRepository.delete(reservation);
                }
            }
        }
    }
}
