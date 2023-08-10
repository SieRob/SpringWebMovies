package be.vdab.movies.controllers;

import be.vdab.movies.services.ReservatieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ReservatieController {
    private final ReservatieService reservatieService;

    public ReservatieController(ReservatieService reservatieService) {
        this.reservatieService = reservatieService;
    }


    @GetMapping("reservaties/{klantid}/{mandje}")
    ArrayList<String> nieuweReservatie(@PathVariable long klantid, @PathVariable String mandje){
        return (reservatieService.nieuweReservatie(klantid, mandje));
    }
}
