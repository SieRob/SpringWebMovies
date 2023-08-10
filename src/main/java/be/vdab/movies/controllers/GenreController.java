package be.vdab.movies.controllers;

import be.vdab.movies.domain.Genre;
import be.vdab.movies.dto.GenreEnFilms;
import be.vdab.movies.services.GenreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("genres")
public class GenreController {
    private final GenreService genreService;
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    List<Genre> findAll(){
        return genreService.findAll();
    }

    @GetMapping("{id}")
    Optional<List<GenreEnFilms>> findById(@PathVariable long id){
        return genreService.findById(id);
    }
}
