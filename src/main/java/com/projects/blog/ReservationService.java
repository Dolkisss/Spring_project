package com.projects.blog;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ReservationService {

    public static final Logger log = LoggerFactory.getLogger(ReservationController.class);

    public final Map<Long, Reservation> reservationMap = new HashMap<>(Map.of(
            1L, new Reservation(
                    1L,
                    101L,
                    41L,
                    LocalDate.now(),
                    LocalDate.now().plusDays(2),
                    ReservationStatus.APPROVED
            ),
            2L, new Reservation(
                    2L,
                    102L,
                    42L,
                    LocalDate.now(),
                    LocalDate.now().plusDays(3),
                    ReservationStatus.CANCELLED
            ),
            3L, new Reservation(
                    3L,
                    103L,
                    43L,
                    LocalDate.now(),
                    LocalDate.now().plusDays(5),
                    ReservationStatus.APPROVED
            ),
            4L, new Reservation(
                    4L,
                    104L,
                    44L,
                    LocalDate.now(),
                    LocalDate.now().plusDays(4),
                    ReservationStatus.PENDING
            ),
            5L, new Reservation(
                    5L,
                    105L,
                    45L,
                    LocalDate.now(),
                    LocalDate.now().plusDays(7),
                    ReservationStatus.PENDING
            )
    ));

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
}
