package com.victoria.fargutu.unibook.service.reservation;

import com.victoria.fargutu.unibook.repository.db.ReservationRepository;
import com.victoria.fargutu.unibook.repository.db.ScheduleCellRepository;
import com.victoria.fargutu.unibook.repository.model.AuthManager;
import com.victoria.fargutu.unibook.repository.model.reservation.Reservation;
import com.victoria.fargutu.unibook.repository.model.reservation.ReservationResponse;
import com.victoria.fargutu.unibook.repository.model.scheduleCell.ScheduleCell;
import com.victoria.fargutu.unibook.service.exceptions.ClassroomNotFreeException;
import com.victoria.fargutu.unibook.service.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;
    private ScheduleCellRepository scheduleCellRepository;
    private AuthManager authManager;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, ScheduleCellRepository scheduleCellRepository, AuthManager authManager) {
        this.reservationRepository = reservationRepository;
        this.scheduleCellRepository = scheduleCellRepository;
        this.authManager = authManager;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public ReservationResponse createReservation(Reservation reservation) {
        //TODO if is possible then make reservation
        List<ScheduleCell> scheduleCells = scheduleCellRepository.findAll();
        List<Reservation> reservations = reservationRepository.findAll();
        for (ScheduleCell scheduleCell : scheduleCells) {
            if (scheduleCell.getDay().equals(reservation.getDay())) {
                if (scheduleCell.getHour().equals(reservation.getHour())) {
                    if (scheduleCell.getWeekType().equals(reservation.getWeekType())) {
                        if (scheduleCell.getClassroom().getId().equals(reservation.getClassroom().getId())) {
                            throw new ClassroomNotFreeException();
                        }
                    }
                }
            }
        }
        Calendar calendarMyReservation = Calendar.getInstance();
        calendarMyReservation.setTime(reservation.getDate());

        Calendar calendar = Calendar.getInstance();

        for (Reservation tempReservation : reservations) {
            calendar.setTime(tempReservation.getDate());
            calendar.set(Calendar.HOUR_OF_DAY, calendarMyReservation.get(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendarMyReservation.get(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendarMyReservation.get(Calendar.SECOND));
            calendar.set(Calendar.MILLISECOND, calendarMyReservation.get(Calendar.MILLISECOND));
            if (tempReservation.getDay().equals(reservation.getDay())) {
                if (calendar.getTime().equals(calendarMyReservation.getTime())) {
                    if (tempReservation.getHour().equals(reservation.getHour())) {
                        if (tempReservation.getWeekType().equals(reservation.getWeekType())) {
                            if (tempReservation.getClassroom().getId().equals(reservation.getClassroom().getId())) {
                                throw new ClassroomNotFreeException();
                            }
                        }
                    }
                }
            }
        }

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
