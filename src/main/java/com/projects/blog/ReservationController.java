package com.projects.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    public static final Logger log = LoggerFactory.getLogger(ReservationController.class);

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping()
    public List<Reservation> getAllReservations() {
        log.info("Called getAllReservations");
        return reservationService.findAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(
            @PathVariable("id") Long id
    ) {
        log.info("Called getReservationById by id = " + id);
        return reservationService.getReservationById(id);
    }

    @GetMapping("/push/{id}")
    public Reservation pushReservationById(
            @PathVariable("id") Long id
    ) {
        log.info("Called getReservationById by id = " + id);
        return reservationService.pushReservationById(id);
    }
}
