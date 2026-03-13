package com.projects.blog;

import org.jspecify.annotations.Nullable;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ReservationService {

    public static final Logger log = LoggerFactory.getLogger(ReservationController.class);

    private final AtomicLong counter;

    public final Map<Long, Reservation> reservationMap;

    public ReservationService() {
        reservationMap = new HashMap<>();
        counter = new AtomicLong();
    }

    public Reservation getReservationById(
            Long id
    ) {
        if (!reservationMap.containsKey(id)) {
            throw new NoSuchElementException("Not found reservation by id = " + id);
        }
        return reservationMap.get(id);
    }

    public List<Reservation> findAllReservations() {
        return reservationMap.values().stream().toList();
    }

    public Reservation pushReservationById(Long id) {
        try {
            reservationMap.put(
                    id, new Reservation(
                            id,
                            (100L + id),
                            (40L + id),
                            LocalDate.now(),
                            LocalDate.now().plusDays(id),
                            ReservationStatus.APPROVED

                    ));
            return reservationMap.get(id);
        } catch (Exception e) {
            log.info("Called Exception: " + e);
            throw e;
        }
    }

    public Reservation createReservation(
            Reservation reservationToCreate
    ) {
        if (reservationToCreate.id() != null) {
            throw new IllegalArgumentException("ID should be empty!");
        }
        if (reservationToCreate.status() != null) {
            throw new IllegalArgumentException("Status should be empty!");
        }

        var newReservation = new Reservation(
                counter.incrementAndGet(),
                reservationToCreate.userId(),
                reservationToCreate.roomId(),
                reservationToCreate.startDate(),
                reservationToCreate.endDate(),
                ReservationStatus.PENDING
        );

        reservationMap.put(newReservation.id(), newReservation);
        return newReservation;
    }

    public void deleteReservation(
            Long id
    ) {
        if (!reservationMap.containsKey(id)) {
            throw new NoSuchElementException("Not found reservation with id =" + id);
        }
        reservationMap.remove(id);
    }
}
