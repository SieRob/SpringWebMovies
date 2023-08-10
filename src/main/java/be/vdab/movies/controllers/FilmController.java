package be.vdab.movies.controllers;

import be.vdab.movies.domain.Films;
import be.vdab.movies.exceptions.FilmNietGevondenException;
import be.vdab.movies.services.FilmService;
import be.vdab.movies.services.GenreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.stream.Stream;

@RestController
public class FilmController {

    private final FilmService filmService;
    private record idNaamPrijs (long id, String titel, BigDecimal prijs){
        idNaamPrijs(Films film){
            this(film.getId(), film.getTitel(), film.getPrijs());
        }
    }
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("film/{id}")
    Films getById(@PathVariable long id){
        return filmService.getById(id).orElseThrow(() -> new FilmNietGevondenException());
    }

    @GetMapping("mandje/{mandje}")
    Stream<idNaamPrijs> getMandjeItems(@PathVariable String mandje)
    {
        return filmService.getMandjeItems(mandje)
                .stream()
                .map(item-> new idNaamPrijs(item));
    }

}
