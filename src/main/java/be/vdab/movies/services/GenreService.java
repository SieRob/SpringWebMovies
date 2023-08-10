package be.vdab.movies.services;

import be.vdab.movies.domain.Genre;
import be.vdab.movies.dto.GenreEnFilms;
import be.vdab.movies.repositories.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GenreService {

    private GenreRepository genreRepo;

    public GenreService(GenreRepository genreRepo) {
        this.genreRepo = genreRepo;
    }

    public List<Genre> findAll(){
        return genreRepo.findAll();
    }

    public Optional<List<GenreEnFilms>> findById(long id) {
        return genreRepo.findById(id);
    }
}
